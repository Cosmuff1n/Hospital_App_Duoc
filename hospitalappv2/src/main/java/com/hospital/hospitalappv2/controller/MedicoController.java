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

import com.hospital.hospitalappv2.dto.MedicoDTO;
import com.hospital.hospitalappv2.service.MedicoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @GetMapping
    public List<MedicoDTO> findAll() {
        return medicoService.findAll();
    }

    @GetMapping("/{id}")
    public MedicoDTO findById(@PathVariable Long id) {
        return medicoService.findById(id);
    }

    @PostMapping
    public ResponseEntity<MedicoDTO> create(@Valid @RequestBody MedicoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoService.create(dto));
    }

    @PutMapping("/{id}")
    public MedicoDTO update(@PathVariable Long id, @Valid @RequestBody MedicoDTO dto) {
        return medicoService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        medicoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/reportes/especialidades")
    public List<MedicoDTO> getReporteEspecialidades() {
        return medicoService.getReporteEspecialidades();
    }
}