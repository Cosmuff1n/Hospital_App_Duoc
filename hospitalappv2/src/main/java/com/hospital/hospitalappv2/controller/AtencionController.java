package com.hospital.hospitalappv2.controller;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.hospitalappv2.dto.AtencionDTO;
import com.hospital.hospitalappv2.service.AtencionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/atenciones")
public class AtencionController {

    private final AtencionService atencionService;

    public AtencionController(AtencionService atencionService) {
        this.atencionService = atencionService;
    }

    @GetMapping
    public ResponseEntity<List<AtencionDTO>> findAll() {
        return ResponseEntity.ok(atencionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtencionDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(atencionService.findById(id));
    }

    @PostMapping
    public ResponseEntity<AtencionDTO> create(@Valid @RequestBody AtencionDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(atencionService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtencionDTO> update(@PathVariable Long id, @Valid @RequestBody AtencionDTO dto) {
        return ResponseEntity.ok(atencionService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        atencionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/reportes/paciente/{idPaciente}")
    public ResponseEntity<List<AtencionDTO>> getReportePorPaciente(@PathVariable Long idPaciente) {
        return ResponseEntity.ok(atencionService.getReportePorPaciente(idPaciente));
    }

    @GetMapping("/reportes/medico/{idMedico}")
    public ResponseEntity<List<AtencionDTO>> getReportePorMedico(@PathVariable Long idMedico) {
        return ResponseEntity.ok(atencionService.getReportePorMedico(idMedico));
    }

    @GetMapping("/reportes/fecha")
    public ResponseEntity<List<AtencionDTO>> getReportePorFecha(
            @RequestParam("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha
    ) {
        return ResponseEntity.ok(atencionService.getReportePorFecha(fecha));
    }
}