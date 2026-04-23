package com.hospital.hospitalapp.service;

import com.hospital.hospitalapp.dto.TipoUsuarioDTO;
import com.hospital.hospitalapp.dto.reporte.CostoPorTipoUsuarioDTO;
import java.util.List;

public interface TipoUsuarioService {
    List<TipoUsuarioDTO> findAll();
    TipoUsuarioDTO findById(Long id);
    TipoUsuarioDTO create(TipoUsuarioDTO dto);
    TipoUsuarioDTO update(Long id, TipoUsuarioDTO dto);
    void delete(Long id);
    List<CostoPorTipoUsuarioDTO> getReporteCostosPorTipoUsuario();
}