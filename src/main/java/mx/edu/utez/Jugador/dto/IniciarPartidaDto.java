package mx.edu.utez.Jugador.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IniciarPartidaDto {

    @NotNull(message = "El id del jugador no puede ser nulo")
    private Long jugadorId;

    @NotNull(message = "La apuesta no puede ser nula")
    @Min(value = 1, message = "La apuesta debe ser mayor a 0")
    private double apuesta;

}
