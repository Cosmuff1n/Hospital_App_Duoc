package com.hospital.hospitalapp.serviceimplementation;


import com.hospital.hospitalapp.dto.FichaPacienteDto;
import com.hospital.hospitalapp.entity.FichaPaciente;
import com.hospital.hospitalapp.entity.Paciente;
import com.hospital.hospitalapp.repository.FichaPacienteRepository;
import com.hospital.hospitalapp.repository.PacienteRepository;
import com.hospital.hospitalapp.service.FichaPacienteService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class FichaPacienteServiceImpl implements FichaPacienteService {

    private final FichaPacienteRepository fichaPacienteRepository;
    private final PacienteRepository pacienteRepository;

    public FichaPacienteServiceImpl(
            FichaPacienteRepository fichaPacienteRepository,
            PacienteRepository pacienteRepository
    ) {
        this.fichaPacienteRepository = fichaPacienteRepository;
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FichaPacienteDto> findAll() {
        List<FichaPaciente> entities = fichaPacienteRepository.findAll();
        List<FichaPacienteDto> dtos = new ArrayList<>();

        for (FichaPaciente entity : entities) {
            FichaPacienteDto dto = toDTO(entity);
            dtos.add(dto);
        }

        return dtos;
    }

    @Override
    @Transactional(readOnly = true)
    public FichaPacienteDto findById(Long idPaciente) {
        FichaPaciente entity = getEntity(idPaciente);
        return toDTO(entity);
    }

    @Override
    public FichaPacienteDto create(FichaPacienteDto dto) {
        boolean fichaExiste = fichaPacienteRepository.existsById(dto.getIdPaciente());

        if (fichaExiste) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La ficha del paciente ya existe");
        }

        Paciente paciente = getPaciente(dto.getIdPaciente());

        FichaPaciente entity = new FichaPaciente();
        entity.setPaciente(paciente);
        entity.setDatosPersonales(dto.getDatosPersonales());
        entity.setDatosPersonales2(dto.getDatosPersonales2());
        entity.setDatosPersonales3(dto.getDatosPersonales3());
        entity.setDatosPersonales4(dto.getDatosPersonales4());
        entity.setDatosPersonales5(dto.getDatosPersonales5());

        FichaPaciente savedEntity = fichaPacienteRepository.save(entity);
        return toDTO(savedEntity);
    }

    @Override
    public FichaPacienteDto update(Long idPaciente, FichaPacienteDto dto) {
        FichaPaciente entity = getEntity(idPaciente);

        entity.setDatosPersonales(dto.getDatosPersonales());
        entity.setDatosPersonales2(dto.getDatosPersonales2());
        entity.setDatosPersonales3(dto.getDatosPersonales3());
        entity.setDatosPersonales4(dto.getDatosPersonales4());
        entity.setDatosPersonales5(dto.getDatosPersonales5());

        FichaPaciente savedEntity = fichaPacienteRepository.save(entity);
        return toDTO(savedEntity);
    }

    @Override
    public void delete(Long idPaciente) {
        FichaPaciente entity = getEntity(idPaciente);
        fichaPacienteRepository.delete(entity);
    }

    private FichaPaciente getEntity(Long idPaciente) {
        Optional<FichaPaciente> optionalFichaPaciente = fichaPacienteRepository.findById(idPaciente);

        if (optionalFichaPaciente.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ficha de paciente no encontrada");
        }

        return optionalFichaPaciente.get();
    }

    private Paciente getPaciente(Long idPaciente) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(idPaciente);

        if (optionalPaciente.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado");
        }

        return optionalPaciente.get();
    }

    private FichaPacienteDto toDTO(FichaPaciente entity) {
        FichaPacienteDto dto = new FichaPacienteDto();
        dto.setIdPaciente(entity.getIdPaciente());
        dto.setDatosPersonales(entity.getDatosPersonales());
        dto.setDatosPersonales2(entity.getDatosPersonales2());
        dto.setDatosPersonales3(entity.getDatosPersonales3());
        dto.setDatosPersonales4(entity.getDatosPersonales4());
        dto.setDatosPersonales5(entity.getDatosPersonales5());
        return dto;
    }
}