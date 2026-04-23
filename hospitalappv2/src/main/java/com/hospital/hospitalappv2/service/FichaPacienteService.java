package com.hospital.hospitalapp.service;


import com.hospital.hospitalapp.dto.FichaPacienteDTO;
import java.util.List;

public interface FichaPacienteService {
    List<FichaPacienteDTO> findAll();
    FichaPacienteDTO findById(Long idPaciente);
    FichaPacienteDTO create(FichaPacienteDTO dto);
    FichaPacienteDTO update(Long idPaciente, FichaPacienteDTO dto);
    void delete(Long idPaciente);
}