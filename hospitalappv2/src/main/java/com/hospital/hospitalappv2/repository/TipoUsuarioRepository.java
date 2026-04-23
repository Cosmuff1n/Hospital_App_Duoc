package com.hospital.hospitalappv2.repository;

import com.hospital.hospitalappv2.model.TipoUsuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long> {

    @Query("""
            select tu.id, tu.nombre, coalesce(sum(a.costo), 0)
            from TipoUsuario tu
            left join tu.pacientes p
            left join pu.atenciones a
            group by tu.id, tu.nombre
            order by tu.nombre
            """)
    List<Object[]> findReporteCostosPorTipoUsuario();
}