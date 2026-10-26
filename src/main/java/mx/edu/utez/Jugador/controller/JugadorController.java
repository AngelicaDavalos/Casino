package mx.edu.utez.Jugador.controller;


import jakarta.validation.Valid;
import mx.edu.utez.Jugador.dto.JugadorResponseDto;
import mx.edu.utez.Jugador.dto.RecargarSaldoDto;
import mx.edu.utez.Jugador.service.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jugadores")
public class JugadorController {

    @Autowired
    private JugadorService jugadorService;

    // recargarSaldo
    @PatchMapping("/{id}/recargar")
    public ResponseEntity<JugadorResponseDto> recargarSaldo(
            @PathVariable Long id,
            @Valid @RequestBody RecargarSaldoDto dto) {
        return ResponseEntity.ok(jugadorService.recargarSaldo(id, dto));
    }

}
