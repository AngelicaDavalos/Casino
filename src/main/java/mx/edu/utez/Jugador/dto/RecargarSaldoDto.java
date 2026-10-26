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
public class RecargarSaldoDto {

    @NotNull(message = "El monto a recargar no puede ser nulo")
    @Min(value = 1, message = "El monto a recargar debe ser mayor a 0")
    private double monto;
}
