package com.hospital.hospitalapp.repository;

import com.hospital.hospitalapp.entity.FichaPaciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FichaPacienteRepository extends JpaRepository<FichaPaciente, Long> {
}