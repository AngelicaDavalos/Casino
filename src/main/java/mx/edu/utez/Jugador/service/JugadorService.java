package mx.edu.utez.Jugador.service;


import mx.edu.utez.Jugador.dto.JugadorResponseDto;
import mx.edu.utez.Jugador.dto.RecargarSaldoDto;
import mx.edu.utez.Jugador.exeption.customExceptions.CustomNotFoundException;
import mx.edu.utez.Jugador.model.jugador.Jugador;
import mx.edu.utez.Jugador.repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;

    // recargarSaldo -> ⭐ 10 pts
    public JugadorResponseDto recargarSaldo(Long id, RecargarSaldoDto dto) {
        Jugador jugador = jugadorRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("El jugador no existe"));

        jugador.setSaldo(jugador.getSaldo() + dto.getMonto());
        Jugador guardado = jugadorRepository.save(jugador);

        return new JugadorResponseDto(
                guardado.getId(),
                guardado.getNombre(),
                guardado.getSaldo(),
                guardado.isActivo()
        );
    }

}
