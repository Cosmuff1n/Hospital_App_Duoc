package com.hospital.hospitalappv2.service;


import java.util.List;

import com.hospital.hospitalappv2.dto.PacienteDTO;

public interface PacienteService {
    List<PacienteDTO> findAll();
    PacienteDTO findById(Long id);
    PacienteDTO create(PacienteDTO dto);
    PacienteDTO update(Long id, PacienteDTO dto);
    void delete(Long id);
    PacienteDTO getHistorialCompleto(Long idPaciente);
}