package com.hospital.hospitalapp.repository;

package com.hospital.hospitalapp.repository;

import com.hospital.hospitalapp.entity.TipoUsuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long> {

    @Query("""
            select t.id, t.nombre, coalesce(sum(a.costo), 0)
            from TipoUsuario t
            left join t.pacientes p
            left join p.atenciones a
            group by t.id, t.nombre
            order by t.nombre
            """)
    List<Object[]> findReporteCostosPorTipoUsuario();
}