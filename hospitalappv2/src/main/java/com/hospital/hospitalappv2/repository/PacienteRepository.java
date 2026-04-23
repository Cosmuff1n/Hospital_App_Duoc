package com.hospital.hospitalappv2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hospital.hospitalappv2.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @EntityGraph(attributePaths = {"tipoUsuario", "fichaPaciente"})
    @Query("select p from Paciente p where p.idPaciente = :idPaciente")
    Optional<Paciente> findDetalleById(@Param("idPaciente") Long idPaciente);

    boolean existsByRun(String run);
}