package com.hospital.hospitalapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ficha_paciente")
@Data
@NoArgsConstructor
public class FichaPaciente {

    @Id
    @Column(name = "id_paciente")
    private Long idPaciente;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @Column(name = "datos_personales", nullable = false, length = 100)
    private String datosPersonales;

    @Column(name = "datos_personales_2", length = 100)
    private String datosPersonales2;

    @Column(name = "datos_personales_3", length = 100)
    private String datosPersonales3;

    @Column(name = "datos_personales_4", length = 100)
    private String datosPersonales4;

    @Column(name = "datos_personales_5", length = 100)
    private String datosPersonales5;
}
