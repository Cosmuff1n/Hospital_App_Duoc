package com.hospital.hospitalapp.controller;

import com.hospital.hospitalapp.dto.FichaPacienteDto;
import com.hospital.hospitalapp.service.FichaPacienteService;
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
    public ResponseEntity<List<FichaPacienteDto>> findAll() {
        return fichaPacienteService.findAll();
    }

    @GetMapping("/{idPaciente}")
    public ResponseEntity<FichaPacienteDto> findById(@PathVariable Long idPaciente) {
        return fichaPacienteService.findById(idPaciente);
    }

    @PostMapping
    public ResponseEntity<FichaPacienteDto> create(@Valid @RequestBody FichaPacienteDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(fichaPacienteService.create(dto));
    }

    @PutMapping("/{idPaciente}")
    public ResponseEntity<FichaPacienteDto> update(@PathVariable Long idPaciente, @Valid @RequestBody FichaPacienteDto dto) {
        return fichaPacienteService.update(idPaciente, dto);
    }

    @DeleteMapping("/{idPaciente}")
    public ResponseEntity<Void> delete(@PathVariable Long idPaciente) {
        fichaPacienteService.delete(idPaciente);
        return ResponseEntity.noContent().build();
    }
}