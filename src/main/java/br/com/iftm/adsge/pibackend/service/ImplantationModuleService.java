package br.com.iftm.adsge.pibackend.service;

import br.com.iftm.adsge.pibackend.model.ImplantationModule;
import br.com.iftm.adsge.pibackend.model.Module;
import br.com.iftm.adsge.pibackend.model.dto.ImplantationModuleDto;
import br.com.iftm.adsge.pibackend.repository.ModuleImplantationRepository;
import br.com.iftm.adsge.pibackend.repository.ModuleRepository;
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

    private final ModuleImplantationRepository repository;
    private final ModuleRepository moduleRepository;

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
}
