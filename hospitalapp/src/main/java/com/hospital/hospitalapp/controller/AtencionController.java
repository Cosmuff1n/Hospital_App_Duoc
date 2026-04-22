package com.hospital.hospitalapp.controller;

import com.hospital.hospitalapp.dto.AtencionDto;
import com.hospital.hospitalapp.service.AtencionService;
import jakarta.validation.Valid;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/atenciones")
public class AtencionController {

    private final AtencionService atencionService;

    public AtencionController(AtencionService atencionService) {
        this.atencionService = atencionService;
    }

    @GetMapping
    public ResponseEntity<List<AtencionDto>> findAll() {
        return ResponseEntity.ok(atencionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtencionDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(atencionService.findById(id));
    }

    @PostMapping
    public ResponseEntity<AtencionDto> create(@Valid @RequestBody AtencionDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(atencionService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtencionDto> update(@PathVariable Long id, @Valid @RequestBody AtencionDto dto) {
        return ResponseEntity.ok(atencionService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        atencionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/reportes/paciente/{idPaciente}")
    public ResponseEntity<List<AtencionDto>> getReportePorPaciente(@PathVariable Long idPaciente) {
        return ResponseEntity.ok(atencionService.getReportePorPaciente(idPaciente));
    }

    @GetMapping("/reportes/medico/{idMedico}")
    public ResponseEntity<List<AtencionDto>> getReportePorMedico(@PathVariable Long idMedico) {
        return ResponseEntity.ok(atencionService.getReportePorMedico(idMedico));
    }

    @GetMapping("/reportes/fecha")
    public ResponseEntity<List<AtencionDto>> getReportePorFecha(
            @RequestParam("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha
    ) {
        return ResponseEntity.ok(atencionService.getReportePorFecha(fecha));
    }
}