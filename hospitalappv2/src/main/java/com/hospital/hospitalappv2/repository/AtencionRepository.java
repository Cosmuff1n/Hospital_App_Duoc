package com.hospital.hospitalapp.repository;


import com.hospital.hospitalapp.entity.Atencion;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtencionRepository extends JpaRepository<Atencion, Long> {

    List<Atencion> findByPacienteIdPacienteOrderByFechaAtencionDescHoraAtencionDesc(Long idPaciente);

    List<Atencion> findByMedicoIdMedicoOrderByFechaAtencionDescHoraAtencionDesc(Long idMedico);

    List<Atencion> findByFechaAtencionOrderByHoraAtencionAsc(Date fechaAtencion);
}