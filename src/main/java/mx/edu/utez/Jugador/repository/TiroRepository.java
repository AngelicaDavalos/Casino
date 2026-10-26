package mx.edu.utez.Jugador.repository;

import mx.edu.utez.Jugador.model.tiro.Tiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TiroRepository extends JpaRepository<Tiro, Long> {
}
