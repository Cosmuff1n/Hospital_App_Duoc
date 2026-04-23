package com.hospital.hospitalappv2.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.hospitalappv2.model.Atencion;

public interface AtencionRepository extends JpaRepository<Atencion, Long> {

    List<Atencion> findByPacienteIdPacienteOrderByFechaAtencionDescHoraAtencionDesc(Long idPaciente);

    List<Atencion> findByMedicoIdMedicoOrderByFechaAtencionDescHoraAtencionDesc(Long idMedico);

    List<Atencion> findByFechaAtencionOrderByHoraAtencionAsc(Date fechaAtencion);
}