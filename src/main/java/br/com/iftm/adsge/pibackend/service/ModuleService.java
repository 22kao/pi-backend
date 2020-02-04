package br.com.iftm.adsge.pibackend.service;

import br.com.iftm.adsge.pibackend.model.Module;
import br.com.iftm.adsge.pibackend.model.dto.ModuleDto;
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
public class ModuleService {

    private final ModuleRepository repository;

    public List<ModuleDto> findAll() {
        List<Module> list = repository.findAll();
        return list.stream().map(e -> new ModuleDto(e)).collect(Collectors.toList());
    }

    public ModuleDto findById(Integer id) {
        Optional<Module> obj = repository.findById(id);
        Module module = obj.orElseThrow(() ->
                new ResourceNotFoundException(String.format("Module id %s not found", id)));
        return new ModuleDto(module);
    }

    @Transactional
    public ModuleDto save(ModuleDto dto) {
        Module module = new Module(dto.getDescription());
        module = repository.save(module);
        return new ModuleDto(module);
    }

    @Transactional
    public ModuleDto update(Integer id, ModuleDto dto) {
        try {
            Module module = repository.getOne(id);
            module.setDescription(dto.getDescription());
            return new ModuleDto(repository.save(module));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(String.format("Module id %s not found", id));
        }
    }

    public void delete(Integer id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(String.format("Module id %s not found", id));
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
