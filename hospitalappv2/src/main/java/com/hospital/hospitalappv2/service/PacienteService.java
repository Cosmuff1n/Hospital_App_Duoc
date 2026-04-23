package com.hospital.hospitalappv2.service;


import com.hospital.hospitalappv2.dto.PacienteDto;
import java.util.List;

public interface PacienteService {
    List<PacienteDto> findAll();
    PacienteDTO findById(Long id);
    PacienteDTO create(PacienteDto dto);
    PacienteDTO update(Long id, PacienteDto dto);
    void delete(Long id);
    HistorialPacienteReporteDto getHistorialCompleto(Long idPaciente);
}