package br.com.iftm.adsge.pibackend.service;

import br.com.iftm.adsge.pibackend.model.Company;
import br.com.iftm.adsge.pibackend.model.dto.CompanyDTO;
import br.com.iftm.adsge.pibackend.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository repository;

    public List<CompanyDTO> findAll(){
        List<Company> list = repository.findAll();
        return list.stream().map(e -> new CompanyDTO(e)).collect(Collectors.toList());
    }

    public Company save(CompanyDTO dto){
        return repository.save(dto.toEntity());
    }

}
