package com.hospital.hospitalapp.repository;

import cl.duoc.hospitalvm.entity.FichaPaciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FichaPacienteRepository extends JpaRepository<FichaPaciente, Long> {
}