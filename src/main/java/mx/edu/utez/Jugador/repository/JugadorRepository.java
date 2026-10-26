package mx.edu.utez.Jugador.repository;

import mx.edu.utez.Jugador.model.jugador.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {
}
