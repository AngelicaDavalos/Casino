package mx.edu.utez.Jugador.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Jugador.model.partida.EstadoPartida;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TiroResponseDto {

    private Long id;
    private Long partidaId;
    private int valorDado1;
    private int valorDado2;
    private int suma;
    private boolean esGanador;
    private EstadoPartida estadoPartida;
    private double saldoActualJugador;

}
