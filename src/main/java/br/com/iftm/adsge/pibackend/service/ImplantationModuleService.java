package br.com.iftm.adsge.pibackend.service;

import br.com.iftm.adsge.pibackend.model.ImplantationModule;
import br.com.iftm.adsge.pibackend.model.dto.ImplantationModuleDto;
import br.com.iftm.adsge.pibackend.repository.ModuleImplantationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImplantationModuleService {

    private final ModuleImplantationRepository repository;

    public List<ImplantationModuleDto> findAll() {
        List<ImplantationModule> list = repository.findAll();
        return list.stream().map(e -> new ImplantationModuleDto(e)).collect(Collectors.toList());
    }
}
