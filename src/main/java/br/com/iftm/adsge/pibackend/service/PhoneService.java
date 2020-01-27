package br.com.iftm.adsge.pibackend.service;

import br.com.iftm.adsge.pibackend.model.Company;
import br.com.iftm.adsge.pibackend.model.Phone;
import br.com.iftm.adsge.pibackend.model.dto.PhoneCompany;
import br.com.iftm.adsge.pibackend.repository.CompanyRepository;
import br.com.iftm.adsge.pibackend.repository.PhoneRepository;
import br.com.iftm.adsge.pibackend.service.exceptions.DatabaseException;
import br.com.iftm.adsge.pibackend.service.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhoneService {

    private final PhoneRepository repository;

    public List<PhoneCompany> findAll() {
        List<Phone> list = repository.findAll();
        return list.stream().map(e -> new PhoneCompany(e)).collect(Collectors.toList());
    }

    public PhoneCompany findById(Long id) {
        Optional<Phone> obj = repository.findById(id);
        Phone phone = obj.orElseThrow(() ->
                new ResourceNotFoundException(String.format("Company id %s not found", id)));
        return new PhoneCompany(phone);
    }

    public List<Phone> findAllByCompanyId(Integer companyId) {
        return repository.findAllByCompanyId(companyId);
    }

    @Transactional
    public PhoneCompany update(Long id, PhoneCompany dto) {
        Phone phone = repository.getOne(id);
        phone.setPhone(dto.getPhone());
        phone.setUsername(dto.getUsername());
        return new PhoneCompany(repository.save(phone));
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(String.format("Phone id %s not found", id));
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
