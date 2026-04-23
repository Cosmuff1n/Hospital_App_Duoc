package com.hospital.hospitalappv2.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hospital.hospitalappv2.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    boolean existsByRunMedico(String runMedico);

    @Query("""
            select new com.hospital.hospitalappv2.dto.reporte.MedicoEspecialidadDto(
                m.idMedico,
                m.nombreCompleto,
                m.especialidad,
                m.jefeTurno
            )
            from Medico m
            order by m.especialidad, m.nombreCompleto
            """)
    List<Medico> findReporteEspecialidades();
}