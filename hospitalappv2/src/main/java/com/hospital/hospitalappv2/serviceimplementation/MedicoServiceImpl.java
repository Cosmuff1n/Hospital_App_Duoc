package com.hospital.hospitalappv2.serviceimplementation;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.hospital.hospitalappv2.dto.MedicoDTO;
import com.hospital.hospitalappv2.model.Medico;
import com.hospital.hospitalappv2.repository.MedicoRepository;
import com.hospital.hospitalappv2.service.MedicoService;

@Service
@Transactional
public class MedicoServiceImpl implements MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoServiceImpl(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicoDTO> findAll() {
        List<Medico> entities = medicoRepository.findAll();
        List<MedicoDTO> dtos = new ArrayList<>();

        for (Medico entity : entities) {
            MedicoDTO dto = toDTO(entity);
            dtos.add(dto);
        }

        return dtos;
    }

    @Override
    @Transactional(readOnly = true)
    public MedicoDTO findById(Long id) {
        Medico entity = getEntity(id);
        return toDTO(entity);
    }

    @Override
    public MedicoDTO create(MedicoDTO dto) {
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
        return toDTO(savedEntity);
    }

    @Override
    public MedicoDTO update(Long id, MedicoDTO dto) {
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
        return toDTO(savedEntity);
    }

    @Override
    public void delete(Long id) {
        Medico entity = getEntity(id);
        medicoRepository.delete(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicoDTO> getReporteEspecialidades() {
        List<Medico> entities = medicoRepository.findAll();
        List<MedicoDTO> dtos = new ArrayList<>();

        for (Medico entity : entities) {
            MedicoDTO dto = toDTO(entity);
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

    private MedicoDTO toDTO(Medico entity) {
        MedicoDTO dto = new MedicoDTO();
        dto.setIdMedico(entity.getIdMedico());
        dto.setRunMedico(entity.getRunMedico());
        dto.setNombreCompleto(entity.getNombreCompleto());
        dto.setEspecialidad(entity.getEspecialidad());
        dto.setJefeTurno(entity.getJefeTurno());
        return dto;
    }
}