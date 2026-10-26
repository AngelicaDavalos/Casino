package mx.edu.utez.Jugador.repository;

import mx.edu.utez.Jugador.model.partida.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartidaRepository extends JpaRepository<Partida,Long> {
    List<Partida> findByJugadorId(Long jugadorId);
}
