package com.hospital.hospitalapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtencionDto {

    private Long id;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaAtencion;

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private Date horaAtencion;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal costo;

    @NotNull
    private Long idPaciente;

    @NotNull
    private Long idMedico;

    @Size(max = 300)
    private String comentario;

    private String medicoResponsable;
    private String pacienteAtendido;
    private String paciente;
    private String medico;
}
