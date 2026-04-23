package com.hospital.hospitalappv2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.hospitalappv2.model.FichaPaciente;

public interface FichaPacienteRepository extends JpaRepository<FichaPaciente, Long> {
}