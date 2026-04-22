package com.hospital.hospitalapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FichaPacienteDTO {

    @NotNull
    private Long idPaciente;

    @NotBlank
    @Size(max = 100)
    private String datosPersonales;

    @Size(max = 100)
    private String datosPersonales2;

    @Size(max = 100)
    private String datosPersonales3;

    @Size(max = 100)
    private String datosPersonales4;

    @Size(max = 100)
    private String datosPersonales5;
}