package com.hospital.hospitalapp.serviceimplementation;

import com.hospital.hospitalapp.dto.TipoUsuarioDto;
import com.hospital.hospitalapp.entity.TipoUsuario;
import com.hospital.hospitalapp.repository.TipoUsuarioRepository;
import com.hospital.hospitalapp.service.TipoUsuarioService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class TipoUsuarioServiceImpl implements TipoUsuarioService {

    private final TipoUsuarioRepository tipoUsuarioRepository;

    public TipoUsuarioServiceImpl(TipoUsuarioRepository tipoUsuarioRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoUsuarioDto> findAll() {
        List<TipoUsuario> entities = tipoUsuarioRepository.findAll();
        List<TipoUsuarioDto> dtos = new ArrayList<>();

        for (TipoUsuario entity : entities) {
            TipoUsuarioDto dto = toDTO(entity);
            dtos.add(dto);
        }

        return dtos;
    }

    @Override
    @Transactional(readOnly = true)
    public TipoUsuarioDto findById(Long id) {
        TipoUsuario entity = getEntity(id);
        return toDTO(entity);
    }

    @Override
    public TipoUsuarioDto create(TipoUsuarioDto dto) {
        TipoUsuario entity = new TipoUsuario();
        entity.setNombre(dto.getNombre().trim());

        TipoUsuario savedEntity = tipoUsuarioRepository.save(entity);
        return toDTO(savedEntity);
    }

    @Override
    public TipoUsuarioDto update(Long id, TipoUsuarioDto dto) {
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
    public List<TipoUsuarioDto> getReporteCostosPorTipoUsuario() {
        List<Object[]> rows = tipoUsuarioRepository.findReporteCostosPorTipoUsuario();
        List<TipoUsuarioDto> dtos = new ArrayList<>();

        for (Object[] row : rows) {
            TipoUsuarioDto dto = new TipoUsuarioDto();

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

    private TipoUsuarioDto toDTO(TipoUsuario entity) {
        TipoUsuarioDto dto = new TipoUsuarioDto();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        return dto;
    }
}