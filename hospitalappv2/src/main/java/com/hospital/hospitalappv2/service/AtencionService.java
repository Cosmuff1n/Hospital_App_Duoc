package com.hospital.hospitalappv2.service;

import java.util.Date;
import java.util.List;

import com.hospital.hospitalappv2.dto.AtencionDTO;

public interface AtencionService {
    List<AtencionDTO> findAll();
    AtencionDTO findById(Long id);
    AtencionDTO create(AtencionDTO dto);
    AtencionDTO update(Long id, AtencionDTO dto);
    void delete(Long id);
    List<AtencionDTO>getReportePorPaciente(Long idPaciente);
    List<AtencionDTO> getReportePorMedico(Long idMedico);
    List<AtencionDTO> getReportePorFecha(Date fecha);
}