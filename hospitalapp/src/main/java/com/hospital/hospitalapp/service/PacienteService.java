package com.hospital.hospitalapp.service;


import com.hospital.hospitalapp.dto.PacienteDto;
import com.hospital.hospitalapp.dto.reporte.HistorialPacienteReporteDto;
import java.util.List;

public interface PacienteService {
    List<PacienteDto> findAll();
    PacienteDTO findById(Long id);
    PacienteDTO create(PacienteDto dto);
    PacienteDTO update(Long id, PacienteDto dto);
    void delete(Long id);
    HistorialPacienteReporteDto getHistorialCompleto(Long idPaciente);
}