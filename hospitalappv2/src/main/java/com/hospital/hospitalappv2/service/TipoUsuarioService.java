package com.hospital.hospitalappv2.service;

import com.hospital.hospitalappv2.dto.TipoUsuarioDTO;
import java.util.List;

public interface TipoUsuarioService {
    List<TipoUsuarioDTO> findAll();
    TipoUsuarioDTO findById(Long id);
    TipoUsuarioDTO create(TipoUsuarioDTO dto);
    TipoUsuarioDTO update(Long id, TipoUsuarioDTO dto);
    void delete(Long id);
    List<CostoPorTipoUsuarioDTO> getReporteCostosPorTipoUsuario();
}