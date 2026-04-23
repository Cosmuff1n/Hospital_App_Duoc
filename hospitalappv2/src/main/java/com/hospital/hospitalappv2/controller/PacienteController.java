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

import com.hospital.hospitalappv2.dto.PacienteDTO;
import com.hospital.hospitalappv2.service.PacienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> findAll() {
        return (ResponseEntity<List<PacienteDTO>>) pacienteService.findAll();
    }

    @GetMapping("/{id}")
    public PacienteDTO findById(@PathVariable Long id) {
        return pacienteService.findById(id);
    }

    @PostMapping
    public ResponseEntity<PacienteDTO> create(@Valid @RequestBody PacienteDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.create(dto));
    }

    @PutMapping("/{id}")
    public PacienteDTO update(@PathVariable Long id, @Valid @RequestBody PacienteDTO dto) {
        return pacienteService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pacienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/historial")
    public PacienteDTO getHistorial(@PathVariable Long id) {
        return pacienteService.getHistorialCompleto(id);
    }
}