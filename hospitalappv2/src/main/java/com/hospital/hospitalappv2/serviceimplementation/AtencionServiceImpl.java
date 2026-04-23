package com.hospital.hospitalappv2.serviceimplementation;

import com.hospital.hospitalappv2.dto.AtencionDTO;
import com.hospital.hospitalappv2.model.Atencion;
import com.hospital.hospitalappv2.model.Medico;
import com.hospital.hospitalappv2.model.Paciente;
import com.hospital.hospitalappv2.repository.AtencionRepository;
import com.hospital.hospitalappv2.repository.MedicoRepository;
import com.hospital.hospitalappv2.repository.PacienteRepository;
import com.hospital.hospitalappv2.service.AtencionService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class AtencionServiceImpl implements AtencionService {

    private final AtencionRepository atencionRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    public AtencionServiceImpl(
            AtencionRepository atencionRepository,
            PacienteRepository pacienteRepository,
            MedicoRepository medicoRepository
    ) {
        this.atencionRepository = atencionRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AtencionDTO> findAll() {
        List<Atencion> entities = atencionRepository.findAll();
        List<AtencionDTO> dtos = new ArrayList<>();

        for (Atencion entity : entities) {
            AtencionDTO dto = toDTO(entity);
            dtos.add(dto);
        }

        return dtos;
    }

    @Override
    @Transactional(readOnly = true)
    public AtencionDTO findById(Long id) {
        Atencion entity = getEntity(id);
        return toDTO(entity);
    }

    @Override
    public AtencionDTO create(AtencionDTO dto) {
        Paciente paciente = getPaciente(dto.getIdPaciente());
        Medico medico = getMedico(dto.getIdMedico());

        Atencion entity = new Atencion();
        entity.setFechaAtencion(dto.getFechaAtencion());
        entity.setHoraAtencion(dto.getHoraAtencion());
        entity.setCosto(dto.getCosto());
        entity.setPaciente(paciente);
        entity.setMedico(medico);
        entity.setComentario(dto.getComentario());

        Atencion savedEntity = atencionRepository.save(entity);
        return toDTO(savedEntity);
    }

    @Override
    public AtencionDTO update(Long id, AtencionDTO dto) {
        Atencion entity = getEntity(id);
        Paciente paciente = getPaciente(dto.getIdPaciente());
        Medico medico = getMedico(dto.getIdMedico());

        entity.setFechaAtencion(dto.getFechaAtencion());
        entity.setHoraAtencion(dto.getHoraAtencion());
        entity.setCosto(dto.getCosto());
        entity.setPaciente(paciente);
        entity.setMedico(medico);
        entity.setComentario(dto.getComentario());

        Atencion savedEntity = atencionRepository.save(entity);
        return toDTO(savedEntity);
    }

    @Override
    public void delete(Long id) {
        Atencion entity = getEntity(id);
        atencionRepository.delete(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AtencionDTO> getReportePorPaciente(Long idPaciente) {
        getPaciente(idPaciente);

        List<Atencion> entities = atencionRepository.findByPacienteIdPacienteOrderByFechaAtencionDescHoraAtencionDesc(idPaciente);
        List<AtencionDTO> dtos = new ArrayList<>();

        for (Atencion entity : entities) {
            AtencionDTO dto = toDTO(entity);
            dto.setMedicoResponsable(entity.getMedico().getNombreCompleto());
            dtos.add(dto);
        }

        return dtos;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AtencionDTO> getReportePorMedico(Long idMedico) {
        getMedico(idMedico);

        List<Atencion> entities = atencionRepository.findByMedicoIdMedicoOrderByFechaAtencionDescHoraAtencionDesc(idMedico);
        List<AtencionDTO> dtos = new ArrayList<>();

        for (Atencion entity : entities) {
            AtencionDTO dto = toDTO(entity);
            dto.setPacienteAtendido(entity.getPaciente().getNombres() + " " + entity.getPaciente().getApellidos());
            dtos.add(dto);
        }

        return dtos;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AtencionDTO> getReportePorFecha(Date fecha) {
        List<Atencion> entities = atencionRepository.findByFechaAtencionOrderByHoraAtencionAsc(fecha);
        List<AtencionDTO> dtos = new ArrayList<>();

        for (Atencion entity : entities) {
            AtencionDTO dto = toDTO(entity);
            dto.setPaciente(entity.getPaciente().getNombres() + " " + entity.getPaciente().getApellidos());
            dto.setMedico(entity.getMedico().getNombreCompleto());
            dtos.add(dto);
        }

        return dtos;
    }

    private Atencion getEntity(Long id) {
        Optional<Atencion> optionalAtencion = atencionRepository.findById(id);

        if (optionalAtencion.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Atención no encontrada");
        }

        return optionalAtencion.get();
    }

    private Paciente getPaciente(Long idPaciente) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(idPaciente);

        if (optionalPaciente.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado");
        }

        return optionalPaciente.get();
    }

    private Medico getMedico(Long idMedico) {
        Optional<Medico> optionalMedico = medicoRepository.findById(idMedico);

        if (optionalMedico.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico no encontrado");
        }

        return optionalMedico.get();
    }

    private AtencionDTO toDTO(Atencion entity) {
        AtencionDTO dto = new AtencionDTO();
        dto.setId(entity.getId());
        dto.setFechaAtencion(entity.getFechaAtencion());
        dto.setHoraAtencion(entity.getHoraAtencion());
        dto.setCosto(entity.getCosto());
        dto.setIdPaciente(entity.getPaciente().getIdPaciente());
        dto.setIdMedico(entity.getMedico().getIdMedico());
        dto.setComentario(entity.getComentario());
        return dto;
    }
}