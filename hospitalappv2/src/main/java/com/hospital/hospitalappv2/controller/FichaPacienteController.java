package com.hospital.hospitalappv2.controller;

import com.hospital.hospitalappv2.dto.FichaPacienteDTO;
import com.hospital.hospitalappv2.service.FichaPacienteService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fichas-paciente")
public class FichaPacienteController {

    private final FichaPacienteService fichaPacienteService;

    public FichaPacienteController(FichaPacienteService fichaPacienteService) {
        this.fichaPacienteService = fichaPacienteService;
    }

    @GetMapping
    public ResponseEntity<List<FichaPacienteDTO>> findAll() {
        return fichaPacienteService.findAll();
    }

    @GetMapping("/{idPaciente}")
    public ResponseEntity<FichaPacienteDTO> findById(@PathVariable Long idPaciente) {
        return fichaPacienteService.findById(idPaciente);
    }

    @PostMapping
    public ResponseEntity<FichaPacienteDTO> create(@Valid @RequestBody FichaPacienteDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(fichaPacienteService.create(dto));
    }

    @PutMapping("/{idPaciente}")
    public ResponseEntity<FichaPacienteDTO> update(@PathVariable Long idPaciente, @Valid @RequestBody FichaPacienteDTO dto) {
        return fichaPacienteService.update(idPaciente, dto);
    }

    @DeleteMapping("/{idPaciente}")
    public ResponseEntity<Void> delete(@PathVariable Long idPaciente) {
        fichaPacienteService.delete(idPaciente);
        return ResponseEntity.noContent().build();
    }
}