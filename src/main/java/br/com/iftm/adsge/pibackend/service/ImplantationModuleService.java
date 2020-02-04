package br.com.iftm.adsge.pibackend.service;

import br.com.iftm.adsge.pibackend.model.ImplantationModule;
import br.com.iftm.adsge.pibackend.model.Module;
import br.com.iftm.adsge.pibackend.model.Observation;
import br.com.iftm.adsge.pibackend.model.User;
import br.com.iftm.adsge.pibackend.model.dto.ImplantationModuleDto;
import br.com.iftm.adsge.pibackend.model.dto.ObservationDto;
import br.com.iftm.adsge.pibackend.repository.ImplantationModuleRepository;
import br.com.iftm.adsge.pibackend.repository.ModuleRepository;
import br.com.iftm.adsge.pibackend.repository.UserRepository;
import br.com.iftm.adsge.pibackend.service.exceptions.DatabaseException;
import br.com.iftm.adsge.pibackend.service.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImplantationModuleService {

    private final ImplantationModuleRepository repository;
    private final ModuleRepository moduleRepository;
    private final UserRepository userRepository;

    public List<ImplantationModuleDto> findAll() {
        List<ImplantationModule> list = repository.findAll();
        return list.stream().map(e -> new ImplantationModuleDto(e)).collect(Collectors.toList());
    }

    public ImplantationModuleDto findById(Long id) {
        Optional<ImplantationModule> obj = repository.findById(id);
        ImplantationModule impModule = obj.orElseThrow(() ->
                new ResourceNotFoundException(String.format("Implantation's module id %s not found", id)));
        return new ImplantationModuleDto(impModule);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(String.format("Implantation's module id %s not found", id));
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Transactional
    public ImplantationModuleDto update(Long id, ImplantationModuleDto impModuleDto) {
        try {
            ImplantationModule impModule = repository.getOne(id);
            updateData(impModule, impModuleDto);
            return new ImplantationModuleDto(impModule);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(String.format("Implantation Module id %s not found", id));
        }
    }

    public List<ObservationDto> findAllObservations(Long id) {
        try {
            ImplantationModule implModule = repository.getOne(id);
            return implModule.getObservations().stream().map(e -> new ObservationDto(e)).collect(Collectors.toList());
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(String.format("Implantation module id %s not found", id));
        }
    }

    @Transactional
    public void addObservation(Long id, ObservationDto obsDto) {
        try {
            ImplantationModule implModule = repository.getOne(id);
            Observation observation = createObservation(implModule, obsDto);
            implModule.getObservations().add(observation);
            repository.save(implModule);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(String.format("Implantation module id %s not found", id));
        }
    }

    private void updateData(ImplantationModule impModule, ImplantationModuleDto impModuleDto) {
        impModule.setStatus(impModuleDto.getStatus());
        impModule.setDtEnd(impModuleDto.getDtEnd());
        impModule.setScore(impModuleDto.getScore());
        impModule.setUserResponsible(impModuleDto.getUserResponsible());

        if (impModuleDto.getModuleId() != impModule.getModule().getId())
            updateModule(impModule, impModuleDto.getModuleId());
    }

    private void updateModule(ImplantationModule impModule, Integer moduleId) {
        try {
            Module module = moduleRepository.getOne(moduleId);
            impModule.setModule(module);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(String.format("Module id %s not found", moduleId));
        }
    }

    private Observation createObservation(ImplantationModule implModule, ObservationDto obsDto) {
        //todo alterar para preupdate = user.authenticated

        User user = userRepository.findByName(obsDto.getUsername());

        if (user == null)
            throw new ResourceNotFoundException(String.format("Username '%s' not found", obsDto.getUsername()));

        return Observation.builder()
                .user(user)
                .implantationModule(implModule)
                .description(obsDto.getDescription())
                .build();
    }
}
