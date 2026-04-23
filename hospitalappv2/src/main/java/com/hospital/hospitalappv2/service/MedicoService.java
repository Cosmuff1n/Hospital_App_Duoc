package com.hospital.hospitalappv2.service;


import java.util.List;

import com.hospital.hospitalappv2.dto.MedicoDTO;

public interface MedicoService {
    List<MedicoDTO> findAll();
    MedicoDTO findById(Long id);
    MedicoDTO create(MedicoDTO dto);
    MedicoDTO update(Long id, MedicoDTO dto);
    void delete(Long id);
    List<MedicoDTO> getReporteEspecialidades();
}