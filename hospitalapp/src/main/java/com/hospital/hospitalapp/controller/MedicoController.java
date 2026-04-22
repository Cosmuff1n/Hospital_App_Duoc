package com.hospital.hospitalapp.controller;

import com.hospital.hospitalapp.dto.MedicoDto;
import com.hospital.hospitalapp.service.MedicoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @GetMapping
    public ResponseEntity<List<MedicoDto>> findAll() {
        return ResponseEntity.ok(medicoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(medicoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<MedicoDto> create(@Valid @RequestBody MedicoDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoDto> update(@PathVariable Long id, @Valid @RequestBody MedicoDto dto) {
        return ResponseEntity.ok(medicoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        medicoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/reportes/especialidades")
    public ResponseEntity<List<MedicoDto>> getReporteEspecialidades() {
        return ResponseEntity.ok(medicoService.getReporteEspecialidades());
    }
}