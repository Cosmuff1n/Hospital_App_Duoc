package com.hospital.hospitalappv2.service;


import java.util.List;

import com.hospital.hospitalappv2.dto.FichaPacienteDTO;

public interface FichaPacienteService {
    List<FichaPacienteDTO> findAll();
    FichaPacienteDTO findById(Long idPaciente);
    FichaPacienteDTO create(FichaPacienteDTO dto);
    FichaPacienteDTO update(Long idPaciente, FichaPacienteDTO dto);
    void delete(Long idPaciente);
}