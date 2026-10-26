package mx.edu.utez.Jugador.controller;


import jakarta.validation.Valid;
import mx.edu.utez.Jugador.dto.IniciarPartidaDto;
import mx.edu.utez.Jugador.dto.PartidaResponseDto;
import mx.edu.utez.Jugador.dto.TiroResponseDto;
import mx.edu.utez.Jugador.service.PartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partidas")
public class PartidaController {

    @Autowired
    private PartidaService partidaService;

    // iniciarPartida
    @PostMapping("/iniciar")
    public ResponseEntity<PartidaResponseDto> iniciarPartida(@Valid @RequestBody IniciarPartidaDto dto) {
        return new ResponseEntity<>(partidaService.iniciarPartida(dto), HttpStatus.CREATED);
    }

    // realizarTiro
    @PostMapping("/{id}/tiro")
    public ResponseEntity<TiroResponseDto> realizarTiro(@PathVariable Long id) {
        return ResponseEntity.ok(partidaService.realizarTiro(id));
    }

    //finalizarPartidaManual
    @PatchMapping("/{id}/finalizar")
    public ResponseEntity<PartidaResponseDto> finalizarPartidaManual(@PathVariable Long id) {
        return ResponseEntity.ok(partidaService.finalizarPartidaManual(id));
    }

    // obtenerHistoriaPartid
    @GetMapping("/jugador/{jugadorId}")
    public ResponseEntity<List<PartidaResponseDto>> obtenerHistorialPartida(@PathVariable Long jugadorId) {
        return ResponseEntity.ok(partidaService.obtenerHistorialPartida(jugadorId));
    }

}
