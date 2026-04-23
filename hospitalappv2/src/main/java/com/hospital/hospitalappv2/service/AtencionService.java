package com.hospital.hospitalapp.service;

import com.hospital.hospitalapp.dto.AtencionDTO;
import com.hospital.hospitalapp.dto.reporte.AtencionFechaReporteDto;
import com.hospital.hospitalapp.dto.reporte.AtencionMedicoReporteDto;
import com.hospital.hospitalapp.dto.reporte.AtencionPacienteReporteDto;
import java.util.Date;
import java.util.List;

public interface AtencionService {
    List<AtencionDTO> findAll();
    AtencionDTO findById(Long id);
    AtencionDTO create(AtencionDTO dto);
    AtencionDTO update(Long id, AtencionDTO dto);
    void delete(Long id);
    List<AtencionPacienteReporteDto> getReportePorPaciente(Long idPaciente);
    List<AtencionMedicoReporteDto> getReportePorMedico(Long idMedico);
    List<AtencionFechaReporteDto> getReportePorFecha(Date fecha);
}