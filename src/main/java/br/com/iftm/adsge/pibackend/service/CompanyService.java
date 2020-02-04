package br.com.iftm.adsge.pibackend.service;

import br.com.iftm.adsge.pibackend.model.Address;
import br.com.iftm.adsge.pibackend.model.Company;
import br.com.iftm.adsge.pibackend.model.Phone;
import br.com.iftm.adsge.pibackend.model.dto.AddressDto;
import br.com.iftm.adsge.pibackend.model.dto.CompanyDto;
import br.com.iftm.adsge.pibackend.model.dto.PhoneDto;
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

    public List<CompanyDto> findAll() {
        List<Company> list = repository.findAll();
        return list
                .stream()
                .map(e -> createCompanyDto(e))
                .collect(Collectors.toList());
    }

    public CompanyDto findById(Integer id) {
        Optional<Company> obj = repository.findById(id);
        Company company = obj.orElseThrow(() ->
                new ResourceNotFoundException(String.format("Company id %s not found", id)));
        return createCompanyDto(company);
    }

    @Transactional
    public CompanyDto save(CompanyDto dto) {
        Company company = dto.toEntity();
        setAddress(company, dto.getAddress());
        setPhoneList(company, dto.getPhones());
        return createCompanyDto(repository.save(company));
    }

    @Transactional
    public CompanyDto update(Integer id, CompanyDto dto) {
        try {
            Company company = repository.getOne(id);
            company.setName(dto.getName());
            company.setDocument(dto.getDocument());
            company.setEmail(dto.getEmail());

            updateAddress(company, dto.getAddress());
            updatePhones(company, dto.getPhones());
            return createCompanyDto(repository.save(company));
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

    private void setPhoneList(Company company, List<PhoneDto> phonesDto) {
        List<String> phoneNumbers = new ArrayList<>();
        List<String> phoneUsernames = new ArrayList<>();
        for (Phone phone : company.getPhones()) {
            phoneNumbers.add(phone.getPhone());
            phoneUsernames.add(phone.getUsername());
        }

        List<PhoneDto> newPhones = new ArrayList<>();
        for (PhoneDto dto : phonesDto) {
            if (!phoneNumbers.contains(dto.getPhone()) || !phoneUsernames.contains(dto.getUsername()))
                newPhones.add(dto);
        }

        company.setPhones(newPhones
                .stream()
                .map(e -> new Phone(null, e.getPhone(), e.getUsername(), company))
                .collect(Collectors.toList()));
    }

    private void setAddress(Company company, AddressDto addressDto) {
        if (addressDto != null) {
            Address address = addressDto.toEntity();
            address.setCompany(company);
            company.setAddress(address);
        }
    }

    private CompanyDto createCompanyDto(Company company) {
        CompanyDto companyDto = new CompanyDto(company);

        if (company.getAddress() != null) {
            AddressDto addressDto = new AddressDto(company.getAddress());
            companyDto.setAddress(addressDto);
        }

        companyDto.setPhones(company.getPhones()
                .stream()
                .map(e -> new PhoneDto(e))
                .collect(Collectors.toList()));
        return companyDto;
    }

    private void updatePhones(Company company, List<PhoneDto> phonesDto) {
        if (phonesDto.isEmpty())
            return;

        if (!company.getPhones().containsAll(phonesDto))
            setPhoneList(company, phonesDto);
    }

    private void updateAddress(Company company, AddressDto addressDto) {
        if (addressDto == null)
            return;

        if (company.getAddress() == null) {
            company.setAddress(addressDto.toEntity());
            company.getAddress().setCompany(company);
        } else {
            company.getAddress().setCity(addressDto.getCity());
            company.getAddress().setNumber(addressDto.getNumber());
            company.getAddress().setStreet(addressDto.getStreet());
            company.getAddress().setState(addressDto.getState());
            company.getAddress().setCep(addressDto.getCep());
        }
    }
}