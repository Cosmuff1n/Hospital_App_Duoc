package com.hospital.hospitalappv2.service;

import java.util.List;

import com.hospital.hospitalappv2.dto.TipoUsuarioDTO;

public interface TipoUsuarioService {
    List<TipoUsuarioDTO> findAll();
    TipoUsuarioDTO findById(Long id);
    TipoUsuarioDTO create(TipoUsuarioDTO dto);
    TipoUsuarioDTO update(Long id, TipoUsuarioDTO dto);
    void delete(Long id);
    List<TipoUsuarioDTO> getReporteCostosPorTipoUsuario();
}