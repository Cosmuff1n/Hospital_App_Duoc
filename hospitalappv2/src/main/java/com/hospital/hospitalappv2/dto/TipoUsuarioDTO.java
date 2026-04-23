package com.hospital.hospitalappv2.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoUsuarioDTO {

    private Long id;

    @NotBlank
    @Size(max = 40)
    private String nombre;

    private BigDecimal totalCostos;
}