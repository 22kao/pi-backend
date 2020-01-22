package br.com.iftm.adsge.pibackend.service;

import br.com.iftm.adsge.pibackend.model.Implantation;
import br.com.iftm.adsge.pibackend.model.dto.ImplantationDTO;
import br.com.iftm.adsge.pibackend.repository.ImplantationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImplantationService {

    private final ImplantationRepository repository;

    public List<ImplantationDTO> findAllByCompanyDocument(String document) {
        List<Implantation> list = repository.findAllByCompanyDocument(document);
        return list.stream().map(e -> new ImplantationDTO(e)).collect(Collectors.toList());
    }
}
