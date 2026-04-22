package com.hospital.hospitalapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoUsuarioDto {

    private Long id;

    @NotBlank
    @Size(max = 40)
    private String nombre;

    private Decimal totalCostos;
}