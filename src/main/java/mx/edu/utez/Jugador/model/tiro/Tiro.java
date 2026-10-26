package mx.edu.utez.Jugador.model.tiro;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Jugador.model.partida.Partida;

@Entity
@Table(name = "tiros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La partida no puede ser nula")
    @ManyToOne
    @JoinColumn(name = "partida_id")
    private Partida partida;

    @NotNull(message = "El valor del dado 1 no puede ser nulo")
    private int valorDado1;

    @NotNull(message = "El valor del dado 2 no puede ser nulo")
    private int valorDado2;

    @NotNull(message = "La suma no puede ser nula")
    private int suma;

    @NotNull(message = "El campo esGanador no puede ser nulo")
    private boolean esGanador;

}
