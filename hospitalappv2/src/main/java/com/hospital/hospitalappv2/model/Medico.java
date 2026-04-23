package com.hospital.hospitalapp.model;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "medico")
@Data
@NoArgsConstructor
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medico")
    private Long idMedico;

    @Column(name = "run_medico", nullable = false, unique = true, length = 10)
    private String runMedico;

    @Column(name = "nombre_completo", nullable = false, length = 100)
    private String nombreCompleto;

    @Column(name = "especialidad", nullable = false, length = 100)
    private String especialidad;

    @Column(name = "jefe_turno", nullable = false, length = 1)
    private String jefeTurno;

    @OneToMany(mappedBy = "medico")
    private List<Atencion> atenciones = new ArrayList<>();
}