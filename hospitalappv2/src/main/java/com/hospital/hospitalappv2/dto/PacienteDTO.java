package com.hospital.hospitalappv2.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteDTO {

    private Long idPaciente;

    @NotBlank
    @Size(max = 8)
    private String run;

    @NotBlank
    @Size(max = 100)
    private String nombres;

    @NotBlank
    @Size(max = 100)
    private String apellidos;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;

    @Email
    @Size(max = 100)
    private String correo;

    @NotNull
    private Long idTipo;

    private String tipoUsuarioNombre;
    private FichaPacienteDTO fichaPaciente;
    private List<AtencionDTO> atenciones;
}