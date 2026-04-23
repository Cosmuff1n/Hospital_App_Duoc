package com.hospital.hospitalapp.repository;


import com.hospital.hospitalapp.dto.reporte.MedicoEspecialidadDto;
import com.hospital.hospitalapp.entity.Medico;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    boolean existsByRunMedico(String runMedico);

    @Query("""
            select new com.hospital.hospitalapp.dto.reporte.MedicoEspecialidadDto(
                m.idMedico,
                m.nombreCompleto,
                m.especialidad,
                m.jefeTurno
            )
            from Medico m
            order by m.especialidad, m.nombreCompleto
            """)
    List<MedicoEspecialidadDto> findReporteEspecialidades();
}