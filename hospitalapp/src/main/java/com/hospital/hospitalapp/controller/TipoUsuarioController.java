package com.hospital.hospitalapp.controller;


import com.hospital.hospitalapp.dto.TipoUsuarioDto;
import com.hospital.hospitalapp.service.TipoUsuarioService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tipos-usuario")
public class TipoUsuarioController {

    private final TipoUsuarioService tipoUsuarioService;

    public TipoUsuarioController(TipoUsuarioService tipoUsuarioService) {
        this.tipoUsuarioService = tipoUsuarioService;
    }

    @GetMapping
    public ResponseEntity<List<TipoUsuarioDto>> findAll() {
        return ResponseEntity.ok(tipoUsuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoUsuarioDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(tipoUsuarioService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TipoUsuarioDto> create(@Valid @RequestBody TipoUsuarioDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoUsuarioService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoUsuarioDto> update(@PathVariable Long id, @Valid @RequestBody TipoUsuarioDto dto) {
        return ResponseEntity.ok(tipoUsuarioService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tipoUsuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/reportes/costos")
    public ResponseEntity<List<TipoUsuarioDto>> getReporteCostos() {
        return ResponseEntity.ok(tipoUsuarioService.getReporteCostosPorTipoUsuario());
    }
}