package com.hospital.hospitalapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteDto {

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
    private FichaPacienteDto fichaPaciente;
    private List<AtencionDto> atenciones;
}