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

import com.hospital.hospitalappv2.dto.FichaPacienteDTO;
import com.hospital.hospitalappv2.service.FichaPacienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/fichas-paciente")
public class FichaPacienteController {

    private final FichaPacienteService fichaPacienteService;

    public FichaPacienteController(FichaPacienteService fichaPacienteService) {
        this.fichaPacienteService = fichaPacienteService;
    }

    @GetMapping
    public List<FichaPacienteDTO> findAll() {
        return fichaPacienteService.findAll();
    }

    @GetMapping("/{idPaciente}")
    public FichaPacienteDTO findById(@PathVariable Long idPaciente) {
        return fichaPacienteService.findById(idPaciente);
    }

    @PostMapping
    public ResponseEntity<FichaPacienteDTO> create(@Valid @RequestBody FichaPacienteDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(fichaPacienteService.create(dto));
    }

    @PutMapping("/{idPaciente}")
    public FichaPacienteDTO update(@PathVariable Long idPaciente, @Valid @RequestBody FichaPacienteDTO dto) {
        return fichaPacienteService.update(idPaciente, dto);
    }

    @DeleteMapping("/{idPaciente}")
    public ResponseEntity<Void> delete(@PathVariable Long idPaciente) {
        fichaPacienteService.delete(idPaciente);
        return ResponseEntity.noContent().build();
    }
}