package com.hospital.hospitalappv2.serviceimplementation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.hospital.hospitalappv2.dto.TipoUsuarioDTO;
import com.hospital.hospitalappv2.model.TipoUsuario;
import com.hospital.hospitalappv2.repository.TipoUsuarioRepository;
import com.hospital.hospitalappv2.service.TipoUsuarioService;

@Service
@Transactional
public class TipoUsuarioServiceImpl implements TipoUsuarioService {

    private final TipoUsuarioRepository tipoUsuarioRepository;

    public TipoUsuarioServiceImpl(TipoUsuarioRepository tipoUsuarioRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoUsuarioDTO> findAll() {
        List<TipoUsuario> entities = tipoUsuarioRepository.findAll();
        List<TipoUsuarioDTO> dtos = new ArrayList<>();

        for (TipoUsuario entity : entities) {
            TipoUsuarioDTO dto = toDTO(entity);
            dtos.add(dto);
        }

        return dtos;
    }

    @Override
    @Transactional(readOnly = true)
    public TipoUsuarioDTO findById(Long id) {
        TipoUsuario entity = getEntity(id);
        return toDTO(entity);
    }

    @Override
    public TipoUsuarioDTO create(TipoUsuarioDTO dto) {
        TipoUsuario entity = new TipoUsuario();
        entity.setNombre(dto.getNombre().trim());

        TipoUsuario savedEntity = tipoUsuarioRepository.save(entity);
        return toDTO(savedEntity);
    }

    @Override
    public TipoUsuarioDTO update(Long id, TipoUsuarioDTO dto) {
        TipoUsuario entity = getEntity(id);
        entity.setNombre(dto.getNombre().trim());

        TipoUsuario savedEntity = tipoUsuarioRepository.save(entity);
        return toDTO(savedEntity);
    }

    @Override
    public void delete(Long id) {
        TipoUsuario entity = getEntity(id);
        tipoUsuarioRepository.delete(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoUsuarioDTO> getReporteCostosPorTipoUsuario() {
        List<Object[]> rows = tipoUsuarioRepository.findReporteCostosPorTipoUsuario();
        List<TipoUsuarioDTO> dtos = new ArrayList<>();

        for (Object[] row : rows) {
            TipoUsuarioDTO dto = new TipoUsuarioDTO();

            dto.setId((Long) row[0]);
            dto.setNombre((String) row[1]);

            if (row[2] instanceof BigDecimal) {
                dto.setTotalCostos((BigDecimal) row[2]);
            } else if (row[2] != null) {
                dto.setTotalCostos(new BigDecimal(row[2].toString()));
            } else {
                dto.setTotalCostos(BigDecimal.ZERO);
            }

            dtos.add(dto);
        }

        return dtos;
    }

    private TipoUsuario getEntity(Long id) {
        Optional<TipoUsuario> optionalTipoUsuario = tipoUsuarioRepository.findById(id);

        if (optionalTipoUsuario.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de usuario no encontrado");
        }

        return optionalTipoUsuario.get();
    }

    private TipoUsuarioDTO toDTO(TipoUsuario entity) {
        TipoUsuarioDTO dto = new TipoUsuarioDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        return dto;
    }
}