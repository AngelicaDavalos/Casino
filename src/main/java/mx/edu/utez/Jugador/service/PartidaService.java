package mx.edu.utez.Jugador.service;

import mx.edu.utez.Jugador.dto.IniciarPartidaDto;
import mx.edu.utez.Jugador.dto.PartidaResponseDto;
import mx.edu.utez.Jugador.dto.TiroResponseDto;
import mx.edu.utez.Jugador.exeption.customExceptions.CustomBadException;
import mx.edu.utez.Jugador.exeption.customExceptions.CustomNotFoundException;
import mx.edu.utez.Jugador.model.jugador.Jugador;
import mx.edu.utez.Jugador.model.partida.EstadoPartida;
import mx.edu.utez.Jugador.model.partida.Partida;
import mx.edu.utez.Jugador.model.tiro.Tiro;
import mx.edu.utez.Jugador.repository.JugadorRepository;
import mx.edu.utez.Jugador.repository.PartidaRepository;
import mx.edu.utez.Jugador.repository.TiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PartidaService {

    @Autowired
    private PartidaRepository partidaRepository;

    @Autowired
    private JugadorRepository jugadorRepository;

    @Autowired
    private TiroRepository tiroRepository;

    //  iniciarPartida
    public PartidaResponseDto iniciarPartida(IniciarPartidaDto dto) {
        Jugador jugador = jugadorRepository.findById(dto.getJugadorId())
                .orElseThrow(() -> new CustomNotFoundException("El jugador no existe"));

        if (!jugador.isActivo()) {
            throw new CustomBadException("El jugador no esta activo");
        }

        if (jugador.getSaldo() < dto.getApuesta()) {
            throw new CustomBadException("Saldo insuficiente para realizar la apuesta");
        }

        // descontar apuesta del saldo
        jugador.setSaldo(jugador.getSaldo() - dto.getApuesta());
        jugadorRepository.save(jugador);

        // cear partida en estado EN_JUEGO
        Partida partida = new Partida();
        partida.setJugador(jugador);
        partida.setApuesta(dto.getApuesta());
        partida.setFecha(LocalDateTime.now());
        partida.setEstado(EstadoPartida.EN_JUEGO);

        Partida guardada = partidaRepository.save(partida);

        return convertirAPartidaDto(guardada);
    }

    //  realizarTiro
    public TiroResponseDto realizarTiro(Long partidaId) {
        Partida partida = partidaRepository.findById(partidaId)
                .orElseThrow(() -> new CustomNotFoundException("La partida no existe"));

        if (partida.getEstado() != EstadoPartida.EN_JUEGO) {
            throw new CustomBadException("La partida ya no esta en juego");
        }

        Random random = new Random();
        int valorDado1 = random.nextInt(6) + 1;
        int valorDado2 = random.nextInt(6) + 1;
        int suma = valorDado1 + valorDado2;

        boolean esGanador = false;
        Jugador jugador = partida.getJugador();

        if (suma == 7 || suma == 11) {
            // gana
            esGanador = true;
            partida.setEstado(EstadoPartida.FINALIZADA);
            jugador.setSaldo(jugador.getSaldo() + (partida.getApuesta() * 2));
            jugadorRepository.save(jugador);
            partidaRepository.save(partida);
        } else if (suma == 2 || suma == 3 || suma == 12) {
            // pierde por burrito
            esGanador = false;
            partida.setEstado(EstadoPartida.FINALIZADA);
            partidaRepository.save(partida);
        } else {
            // Continúa se mantiene EN_JUEGO
            esGanador = false;
        }

        //guardar el tiro como ultimo paso
        Tiro tiro = new Tiro();
        tiro.setPartida(partida);
        tiro.setValorDado1(valorDado1);
        tiro.setValorDado2(valorDado2);
        tiro.setSuma(suma);
        tiro.setEsGanador(esGanador);

        Tiro guardado = tiroRepository.save(tiro);

        return new TiroResponseDto(
                guardado.getId(),
                partida.getId(),
                guardado.getValorDado1(),
                guardado.getValorDado2(),
                guardado.getSuma(),
                guardado.isEsGanador(),
                partida.getEstado(),
                jugador.getSaldo()
        );
    }

    //  fnalizarPartidaManual
    public PartidaResponseDto finalizarPartidaManual(Long id) {
        Partida partida = partidaRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("La partida no existe"));

        if (partida.getEstado() == EstadoPartida.FINALIZADA) {
            throw new CustomBadException("La partida ya se encuentra finalizada");
        }

        partida.setEstado(EstadoPartida.FINALIZADA);
        Partida actualizada = partidaRepository.save(partida);

        return convertirAPartidaDto(actualizada);
    }

    // obtenerHistorialPartida
    public List<PartidaResponseDto> obtenerHistorialPartida(Long jugadorId) {
        if (!jugadorRepository.existsById(jugadorId)) {
            throw new CustomNotFoundException("El jugador no existe");
        }

        List<Partida> lista = partidaRepository.findByJugadorId(jugadorId);
        List<PartidaResponseDto> respuesta = new ArrayList<>();

        for (Partida p : lista) {
            respuesta.add(convertirAPartidaDto(p));
        }

        return respuesta;
    }

    private PartidaResponseDto convertirAPartidaDto(Partida p) {
        return new PartidaResponseDto(
                p.getId(),
                p.getFecha(),
                p.getEstado(),
                p.getJugador().getId(),
                p.getJugador().getNombre(),
                p.getApuesta()
        );
    }

}
