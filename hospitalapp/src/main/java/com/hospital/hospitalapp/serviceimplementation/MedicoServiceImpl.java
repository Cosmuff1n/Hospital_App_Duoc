package com.hospital.hospitalapp.serviceimplementation;


import com.hospital.hospitalapp.dto.MedicoDto;
import com.hospital.hospitalapp.entity.Medico;
import com.hospital.hospitalapp.repository.MedicoRepository;
import com.hospital.hospitalapp.service.MedicoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class MedicoServiceImpl implements MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoServiceImpl(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicoDto> findAll() {
        List<Medico> entities = medicoRepository.findAll();
        List<MedicoDto> dtos = new ArrayList<>();

        for (Medico entity : entities) {
            MedicoDto dto = toDto(entity);
            dtos.add(dto);
        }

        return dtos;
    }

    @Override
    @Transactional(readOnly = true)
    public MedicoDto findById(Long id) {
        Medico entity = getEntity(id);
        return toDto(entity);
    }

    @Override
    public MedicoDto create(MedicoDto dto) {
        boolean medicoExiste = medicoRepository.existsByRunMedico(dto.getRunMedico());

        if (medicoExiste) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ya existe un médico con ese RUN");
        }

        Medico entity = new Medico();
        entity.setRunMedico(dto.getRunMedico().trim());
        entity.setNombreCompleto(dto.getNombreCompleto().trim());
        entity.setEspecialidad(dto.getEspecialidad().trim());
        entity.setJefeTurno(dto.getJefeTurno().trim());

        Medico savedEntity = medicoRepository.save(entity);
        return toDto(savedEntity);
    }

    @Override
    public MedicoDto update(Long id, MedicoDto dto) {
        Medico entity = getEntity(id);

        if (!entity.getRunMedico().equals(dto.getRunMedico())) {
            boolean medicoExiste = medicoRepository.existsByRunMedico(dto.getRunMedico());

            if (medicoExiste) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ya existe un médico con ese RUN");
            }
        }

        entity.setRunMedico(dto.getRunMedico().trim());
        entity.setNombreCompleto(dto.getNombreCompleto().trim());
        entity.setEspecialidad(dto.getEspecialidad().trim());
        entity.setJefeTurno(dto.getJefeTurno().trim());

        Medico savedEntity = medicoRepository.save(entity);
        return toDto(savedEntity);
    }

    @Override
    public void delete(Long id) {
        Medico entity = getEntity(id);
        medicoRepository.delete(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicoDto> getReporteEspecialidades() {
        List<Medico> entities = medicoRepository.findAll();
        List<MedicoDto> dtos = new ArrayList<>();

        for (Medico entity : entities) {
            MedicoDto dto = toDto(entity);
            dtos.add(dto);
        }

        return dtos;
    }

    private Medico getEntity(Long id) {
        Optional<Medico> optionalMedico = medicoRepository.findById(id);

        if (optionalMedico.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico no encontrado");
        }

        return optionalMedico.get();
    }

    private MedicoDto toDto(Medico entity) {
        MedicoDto dto = new MedicoDto();
        dto.setIdMedico(entity.getIdMedico());
        dto.setRunMedico(entity.getRunMedico());
        dto.setNombreCompleto(entity.getNombreCompleto());
        dto.setEspecialidad(entity.getEspecialidad());
        dto.setJefeTurno(entity.getJefeTurno());
        return dto;
    }
}