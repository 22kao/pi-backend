package br.com.iftm.adsge.pibackend.service;

import br.com.iftm.adsge.pibackend.model.Company;
import br.com.iftm.adsge.pibackend.model.Phone;
import br.com.iftm.adsge.pibackend.model.dto.CompanyBasic;
import br.com.iftm.adsge.pibackend.repository.CompanyRepository;
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
public class CompanyService {

    private final CompanyRepository repository;

    public List<CompanyBasic> findAll() {
        List<Company> list = repository.findAll();
        return list.stream().map(e -> new CompanyBasic(e)).collect(Collectors.toList());
    }

    public CompanyBasic findById(Integer id) {
        Optional<Company> company = repository.findById(id);
        return new CompanyBasic(
                company.orElseThrow(() -> new ResourceNotFoundException(String.format("Company id %s not found", id))));
    }

    @Transactional
    public CompanyBasic save(CompanyBasic dto) {
        Company company = repository.save(dto.toEntity());
        return new CompanyBasic(company);
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
    public CompanyBasic update(Integer id, CompanyBasic dto) {
        try {
            Company company = repository.getOne(id);
            company.setName(dto.getName());
            company.setDocument(dto.getDocument());
            company.setEmail(dto.getEmail());
            return new CompanyBasic(repository.save(company));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(String.format("Company id %s not found", id));
        }
    }

    @Transactional
    public List<Phone> addPhoneList(Integer id, List<Phone> phones) {
        try{
            Company company = repository.getOne(id);
            setPhoneList(company, phones);
            return repository.save(company).getPhones();
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(String.format("Company id %s not found",id));
        }
    }

    private void setPhoneList(Company company, List<Phone> phones) {
        company.getPhones().clear();
        for(Phone phone : phones){
            phone.setId(null);
            phone.setCompany(company);
            company.getPhones().add(phone);
        }
    }
}
