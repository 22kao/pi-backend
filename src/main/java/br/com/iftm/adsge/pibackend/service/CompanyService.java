package br.com.iftm.adsge.pibackend.service;

import br.com.iftm.adsge.pibackend.model.Address;
import br.com.iftm.adsge.pibackend.model.Company;
import br.com.iftm.adsge.pibackend.model.dto.CompanyDTO;
import br.com.iftm.adsge.pibackend.model.dto.CompanyFullDTO;
import br.com.iftm.adsge.pibackend.repository.AddressRepository;
import br.com.iftm.adsge.pibackend.repository.CompanyRepository;
import br.com.iftm.adsge.pibackend.service.exceptions.DatabaseException;
import br.com.iftm.adsge.pibackend.service.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository repository;

    public List<CompanyFullDTO> findAll() {
        List<Company> list = repository.findAll();
        return list.stream().map(e -> new CompanyFullDTO(e)).collect(Collectors.toList());
    }

    public CompanyFullDTO findById(Integer id) {
        Optional<Company> company = repository.findById(id);
        return new CompanyFullDTO(company.orElseThrow(() -> new ResourceNotFoundException(id)));
    }

    public CompanyDTO save(CompanyFullDTO dto) {
        Company company = dto.toEntity();
        repository.save(company);
        return new CompanyDTO(company);
    }

    public void delete(Integer id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Transactional
    public CompanyFullDTO update(Integer id, CompanyFullDTO dto) {
        Company company = repository.getOne(id);
        updateData(company, dto);
        company = repository.save(dto.toEntity());
        return new CompanyFullDTO(company);
    }

    private void updateData(Company company, CompanyFullDTO dto) {
        //todo implementar
        //Address address = repository.
    }
}
