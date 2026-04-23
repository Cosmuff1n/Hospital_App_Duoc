package com.hospital.hospitalappv2.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.hospitalappv2.dto.TipoUsuarioDTO;
import com.hospital.hospitalappv2.service.TipoUsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tipos-usuario")
public class TipoUsuarioController {

    private final TipoUsuarioService tipoUsuarioService;

    public TipoUsuarioController(TipoUsuarioService tipoUsuarioService) {
        this.tipoUsuarioService = tipoUsuarioService;
    }

    @GetMapping
    public ResponseEntity<List<TipoUsuarioDTO>> findAll() {
        return tipoUsuarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoUsuarioDTO> findById(@PathVariable Long id) {
        return tipoUsuarioService.findById(id);
    }

    @PostMapping
    public ResponseEntity<TipoUsuarioDTO> create(@Valid @RequestBody TipoUsuarioDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoUsuarioService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoUsuarioDTO> update(@PathVariable Long id, @Valid @RequestBody TipoUsuarioDTO dto) {
        return tipoUsuarioService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tipoUsuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/reportes/costos")
    public ResponseEntity<List<TipoUsuarioDTO>> getReporteCostos() {
        return tipoUsuarioService.getReporteCostosPorTipoUsuario();
    }
}