package com.hospital.hospitalapp.serviceimplementation;

import com.hospital.hospitalapp.dto.AtencionDto;
import com.hospital.hospitalapp.dto.FichaPacienteDto;
import com.hospital.hospitalapp.dto.PacienteDto;
import com.hospital.hospitalapp.entity.Atencion;
import com.hospital.hospitalapp.entity.FichaPaciente;
import com.hospital.hospitalapp.entity.Paciente;
import com.hospital.hospitalapp.entity.TipoUsuario;
import com.hospital.hospitalapp.repository.AtencionRepository;
import com.hospital.hospitalapp.repository.PacienteRepository;
import com.hospital.hospitalapp.repository.TipoUsuarioRepository;
import com.hospital.hospitalapp.service.PacienteService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
    public List<PacienteDto> findAll() {
        List<Paciente> entities = pacienteRepository.findAll();
        List<PacienteDto> dtos = new ArrayList<>();

        for (Paciente entity : entities) {
            PacienteDto dto = toDto(entity);
            dtos.add(dto);
        }

        return dtos;
    }

    @Override
    @Transactional(readOnly = true)
    public PacienteDto findById(Long id) {
        Paciente entity = getEntity(id);
        return toDto(entity);
    }

    @Override
    public PacienteDto create(PacienteDto dto) {
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
        return toDto(savedEntity);
    }

    @Override
    public PacienteDto update(Long id, PacienteDto dto) {
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
        return toDto(savedEntity);
    }

    @Override
    public void delete(Long id) {
        Paciente entity = getEntity(id);
        pacienteRepository.delete(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public PacienteDto getHistorialCompleto(Long idPaciente) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findDetalleById(idPaciente);

        if (optionalPaciente.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado");
        }

        Paciente paciente = optionalPaciente.get();
        PacienteDto dto = toDto(paciente);

        FichaPaciente ficha = paciente.getFichaPaciente();

        if (ficha != null) {
            dto.setFichaPaciente(toFichaDto(ficha));
        }

        List<Atencion> atenciones = atencionRepository.findByPacienteIdPacienteOrderByFechaAtencionDescHoraAtencionDesc(idPaciente);
        List<AtencionDto> atencionDtos = new ArrayList<>();

        for (Atencion atencion : atenciones) {
            AtencionDto atencionDto = toAtencionDtoForPacienteHistorial(atencion);
            atencionDtos.add(atencionDto);
        }

        dto.setAtenciones(atencionDtos);

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

    private PacienteDto toDto(Paciente entity) {
        PacienteDto dto = new PacienteDto();
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

    private FichaPacienteDto toFichaDto(FichaPaciente entity) {
        FichaPacienteDto dto = new FichaPacienteDto();
        dto.setIdPaciente(entity.getIdPaciente());
        dto.setDatosPersonales(entity.getDatosPersonales());
        dto.setDatosPersonales2(entity.getDatosPersonales2());
        dto.setDatosPersonales3(entity.getDatosPersonales3());
        dto.setDatosPersonales4(entity.getDatosPersonales4());
        dto.setDatosPersonales5(entity.getDatosPersonales5());
        return dto;
    }

    private AtencionDto toAtencionDtoForPacienteHistorial(Atencion entity) {
        AtencionDto dto = new AtencionDto();
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