package com.hospital.hospitalappv2.service;

import com.hospital.hospitalappv2.dto.AtencionDTO;
import java.util.Date;
import java.util.List;

public interface AtencionService {
    List<AtencionDTO> findAll();
    AtencionDTO findById(Long id);
    AtencionDTO create(AtencionDTO dto);
    AtencionDTO update(Long id, AtencionDTO dto);
    void delete(Long id);
    List<AtencionPacienteReporteDTO> getReportePorPaciente(Long idPaciente);
    List<AtencionMedicoReporteDTO> getReportePorMedico(Long idMedico);
    List<AtencionFechaReporteDTO> getReportePorFecha(Date fecha);
}