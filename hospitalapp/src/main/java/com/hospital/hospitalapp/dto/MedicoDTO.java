package com.hospital.hospitalapp.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicoDTO {

    private Long idMedico;

    @NotBlank
    @Size(max = 10)
    private String runMedico;

    @NotBlank
    @Size(max = 100)
    private String nombreCompleto;

    @NotBlank
    @Size(max = 100)
    private String especialidad;

    @NotBlank
    @Size(max = 1)
    private String jefeTurno;
}
