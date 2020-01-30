package br.com.iftm.adsge.pibackend.service;

import br.com.iftm.adsge.pibackend.model.Address;
import br.com.iftm.adsge.pibackend.model.Company;
import br.com.iftm.adsge.pibackend.model.Phone;
import br.com.iftm.adsge.pibackend.model.dto.CompanyDetailed;
import br.com.iftm.adsge.pibackend.repository.AddressRepository;
import br.com.iftm.adsge.pibackend.repository.CompanyRepository;
import br.com.iftm.adsge.pibackend.repository.PhoneRepository;
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

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository repository;
    private final AddressRepository addressRepository;
    private final PhoneRepository phoneRepository;

    public List<CompanyDetailed> findAll() {
        List<Company> list = repository.findAll();
        List<CompanyDetailed> detailedList = new ArrayList<>();
        for (Company company : list) {
            detailedList.add(createCompanyDetailed(company));
        }
        return detailedList;
    }

    public CompanyDetailed findById(Integer id) {
        Optional<Company> obj = repository.findById(id);
        Company company = obj.orElseThrow(() ->
                new ResourceNotFoundException(String.format("Company id %s not found", id)));
        return createCompanyDetailed(company);
    }

    @Transactional
    public CompanyDetailed save(CompanyDetailed dto) {
        Company company = dto.toEntity();
        setAddress(company, dto.getAddress());
        setPhoneList(company, dto.getPhones());
        return createCompanyDetailed(repository.save(company));
    }

    @Transactional
    public CompanyDetailed update(Integer id, CompanyDetailed dto) {
        try {
            Company company = repository.getOne(id);
            company.setName(dto.getName());
            company.setDocument(dto.getDocument());
            company.setEmail(dto.getEmail());
            updateAddress(company, dto.getAddress());
            updatePhones(company, dto.getPhones());
            return createCompanyDetailed(repository.save(company));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(String.format("Company id %s not found", id));
        }
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
    public List<Phone> setPhoneList(Integer id, List<Phone> phones) {
        try {
            Company company = repository.getOne(id);
            setPhoneList(company, phones);
            return repository.save(company).getPhones();
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(String.format("Company id %s not found", id));
        }
    }

    private void setPhoneList(Company company, List<Phone> phones) {
        company.getPhones().clear();
        for (Phone phone : phones) {
            phone.setId(null);
            phone.setCompany(company);
            company.getPhones().add(phone);
        }
    }

    private void setAddress(Company company, Address address) {
        if (address != null) {
            address.setId(null);
            address.setCompany(company);
            company.setAddress(address);
        }
    }

    private CompanyDetailed createCompanyDetailed(Company company) {
        CompanyDetailed dto = new CompanyDetailed(company);
        dto.setAddress(company.getAddress());
        dto.setPhones(company.getPhones());
        return dto;
    }

    private void updatePhones(Company company, List<Phone> phones) {
        if (phones.isEmpty())
            company.getPhones().clear();

        if (!company.getPhones().containsAll(phones))
            setPhoneList(company, phones);
    }

    private void updateAddress(Company company, Address newAddress) {
        if (newAddress == null)
            company.setAddress(null);

        Address address = company.getAddress();
        address = addressRepository.getOne(address.getId());
        address.setCity(newAddress.getCity());
        address.setNumber(newAddress.getNumber());
        address.setStreet(newAddress.getStreet());
        address.setState(newAddress.getState());
        address.setCep(newAddress.getCep());
        addressRepository.save(address);
    }

}