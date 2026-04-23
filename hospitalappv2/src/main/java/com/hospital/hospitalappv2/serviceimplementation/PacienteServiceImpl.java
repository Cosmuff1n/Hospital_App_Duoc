package com.hospital.hospitalappv2.serviceimplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.hospital.hospitalappv2.dto.AtencionDTO;
import com.hospital.hospitalappv2.dto.FichaPacienteDTO;
import com.hospital.hospitalappv2.dto.PacienteDTO;
import com.hospital.hospitalappv2.model.Atencion;
import com.hospital.hospitalappv2.model.FichaPaciente;
import com.hospital.hospitalappv2.model.Paciente;
import com.hospital.hospitalappv2.model.TipoUsuario;
import com.hospital.hospitalappv2.repository.AtencionRepository;
import com.hospital.hospitalappv2.repository.PacienteRepository;
import com.hospital.hospitalappv2.repository.TipoUsuarioRepository;
import com.hospital.hospitalappv2.service.PacienteService;

@Service
@Transactional
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;
    private final TipoUsuarioRepository tipoUsuarioRepository;
    private final AtencionRepository atencionRepository;

    public PacienteServiceImpl(
            PacienteRepository pacienteRepository,
            TipoUsuarioRepository tipoUsuarioRepository,
            AtencionRepository atencionRepository
    ) {
        this.pacienteRepository = pacienteRepository;
        this.tipoUsuarioRepository = tipoUsuarioRepository;
        this.atencionRepository = atencionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PacienteDTO> findAll() {
        List<Paciente> entities = pacienteRepository.findAll();
        List<PacienteDTO> dtos = new ArrayList<>();

        for (Paciente entity : entities) {
            PacienteDTO dto = toDTO(entity);
            dtos.add(dto);
        }

        return dtos;
    }

    @Override
    @Transactional(readOnly = true)
    public PacienteDTO findById(Long id) {
        Paciente entity = getEntity(id);
        return toDTO(entity);
    }

    @Override
    public PacienteDTO create(PacienteDTO dto) {
        boolean pacienteExiste = pacienteRepository.existsByRun(dto.getRun());

        if (pacienteExiste) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ya existe un paciente con ese RUN");
        }

        TipoUsuario tipoUsuario = getTipoUsuario(dto.getIdTipo());

        Paciente entity = new Paciente();
        entity.setRun(dto.getRun().trim());
        entity.setNombres(dto.getNombres().trim());
        entity.setApellidos(dto.getApellidos().trim());
        entity.setFechaNacimiento(dto.getFechaNacimiento());
        entity.setCorreo(dto.getCorreo());
        entity.setTipoUsuario(tipoUsuario);

        Paciente savedEntity = pacienteRepository.save(entity);
        return toDTO(savedEntity);
    }

    @Override
    public PacienteDTO update(Long id, PacienteDTO dto) {
        Paciente entity = getEntity(id);

        if (!entity.getRun().equals(dto.getRun())) {
            boolean pacienteExiste = pacienteRepository.existsByRun(dto.getRun());

            if (pacienteExiste) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ya existe un paciente con ese RUN");
            }
        }

        TipoUsuario tipoUsuario = getTipoUsuario(dto.getIdTipo());

        entity.setRun(dto.getRun().trim());
        entity.setNombres(dto.getNombres().trim());
        entity.setApellidos(dto.getApellidos().trim());
        entity.setFechaNacimiento(dto.getFechaNacimiento());
        entity.setCorreo(dto.getCorreo());
        entity.setTipoUsuario(tipoUsuario);

        Paciente savedEntity = pacienteRepository.save(entity);
        return toDTO(savedEntity);
    }

    @Override
    public void delete(Long id) {
        Paciente entity = getEntity(id);
        pacienteRepository.delete(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public PacienteDTO getHistorialCompleto(Long idPaciente) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findDetalleById(idPaciente);

        if (optionalPaciente.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado");
        }

        Paciente paciente = optionalPaciente.get();
        PacienteDTO dto = toDTO(paciente);

        FichaPaciente ficha = paciente.getFichaPaciente();

        if (ficha != null) {
            dto.setFichaPaciente(toFichaDTO(ficha));
        }

        List<Atencion> atenciones = atencionRepository.findByPacienteIdPacienteOrderByFechaAtencionDescHoraAtencionDesc(idPaciente);
        List<AtencionDTO> atencionDTOs = new ArrayList<>();

        for (Atencion atencion : atenciones) {
            AtencionDTO atencionDTO = toAtencionDTOForPacienteHistorial(atencion);
            atencionDTOs.add(atencionDTO);
        }

        dto.setAtenciones(atencionDTOs);

        return dto;
    }

    private Paciente getEntity(Long id) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);

        if (optionalPaciente.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado");
        }

        return optionalPaciente.get();
    }

    private TipoUsuario getTipoUsuario(Long idTipo) {
        Optional<TipoUsuario> optionalTipoUsuario = tipoUsuarioRepository.findById(idTipo);

        if (optionalTipoUsuario.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de usuario no encontrado");
        }

        return optionalTipoUsuario.get();
    }

    private PacienteDTO toDTO(Paciente entity) {
        PacienteDTO dto = new PacienteDTO();
        dto.setIdPaciente(entity.getIdPaciente());
        dto.setRun(entity.getRun());
        dto.setNombres(entity.getNombres());
        dto.setApellidos(entity.getApellidos());
        dto.setFechaNacimiento(entity.getFechaNacimiento());
        dto.setCorreo(entity.getCorreo());
        dto.setIdTipo(entity.getTipoUsuario().getId());
        dto.setTipoUsuarioNombre(entity.getTipoUsuario().getNombre());
        return dto;
    }

    private FichaPacienteDTO toFichaDTO(FichaPaciente entity) {
        FichaPacienteDTO dto = new FichaPacienteDTO();
        dto.setIdPaciente(entity.getIdPaciente());
        dto.setDatosPersonales(entity.getDatosPersonales());
        dto.setDatosPersonales2(entity.getDatosPersonales2());
        dto.setDatosPersonales3(entity.getDatosPersonales3());
        dto.setDatosPersonales4(entity.getDatosPersonales4());
        dto.setDatosPersonales5(entity.getDatosPersonales5());
        return dto;
    }

    private AtencionDTO toAtencionDTOForPacienteHistorial(Atencion entity) {
        AtencionDTO dto = new AtencionDTO();
        dto.setId(entity.getId());
        dto.setFechaAtencion(entity.getFechaAtencion());
        dto.setHoraAtencion(entity.getHoraAtencion());
        dto.setCosto(entity.getCosto());
        dto.setIdPaciente(entity.getPaciente().getIdPaciente());
        dto.setIdMedico(entity.getMedico().getIdMedico());
        dto.setComentario(entity.getComentario());
        dto.setMedicoResponsable(entity.getMedico().getNombreCompleto());
        return dto;
    }
}