package com.hospital.hospitalapp.service;


import com.hospital.hospitalapp.dto.MedicoDTO;
import com.hospital.hospitalapp.dto.reporte.MedicoEspecialidadDto;
import java.util.List;

public interface MedicoService {
    List<MedicoDTO> findAll();
    MedicoDTO findById(Long id);
    MedicoDTO create(MedicoDTO dto);
    MedicoDTO update(Long id, MedicoDTO dto);
    void delete(Long id);
    List<MedicoEspecialidadDto> getReporteEspecialidades();
}