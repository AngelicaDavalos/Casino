package mx.edu.utez.Jugador.model.partida;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Jugador.model.jugador.Jugador;

import java.time.LocalDateTime;


@Entity
@Table(name = "partidas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La fecha no puede ser nula")
    private LocalDateTime fecha;

    @NotNull(message = "El estado no puede ser nulo")
    @Enumerated(EnumType.STRING)
    private EstadoPartida estado;

    @NotNull(message = "El jugador no puede ser nulo")
    @ManyToOne
    @JoinColumn(name = "jugador_id")
    private Jugador jugador;

    @NotNull(message = "La apuesta no puede ser nula")
    private double apuesta;

}
