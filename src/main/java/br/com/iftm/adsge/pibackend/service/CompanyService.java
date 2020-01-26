package br.com.iftm.adsge.pibackend.service;

import br.com.iftm.adsge.pibackend.model.Address;
import br.com.iftm.adsge.pibackend.model.Company;
import br.com.iftm.adsge.pibackend.model.dto.CompanyDTO;
import br.com.iftm.adsge.pibackend.repository.AddressRepository;
import br.com.iftm.adsge.pibackend.repository.CompanyRepository;
import br.com.iftm.adsge.pibackend.service.exceptions.DatabaseException;
import br.com.iftm.adsge.pibackend.service.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository repository;

    public List<CompanyDTO> findAll() {
        List<Company> list = repository.findAll();
        return list.stream().map(e -> new CompanyDTO(e)).collect(Collectors.toList());
    }

    public CompanyDTO findById(Integer id) {
        Optional<Company> company = repository.findById(id);
        return new CompanyDTO(company.orElseThrow(() -> new ResourceNotFoundException(String.format("Company id %s not found", id))));
    }

    @Transactional
    public CompanyDTO save(CompanyDTO dto) {
        Company company = repository.save(dto.toEntity());
        return new CompanyDTO(company);
    }

    public void delete(Integer id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(String.format("Company id %s not found", id));
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Transactional
    public CompanyDTO update(Integer id, CompanyDTO dto) {
        try {
            Company company = repository.getOne(id);
            company.setName(dto.getName());
            company.setDocument(dto.getDocument());
            company.setEmail(dto.getEmail());
            return new CompanyDTO(repository.save(company));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(String.format("Company id %s not found", id));
        }
    }
}
