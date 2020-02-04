package br.com.iftm.adsge.pibackend.service;

import br.com.iftm.adsge.pibackend.model.Address;
import br.com.iftm.adsge.pibackend.model.Company;
import br.com.iftm.adsge.pibackend.model.dto.AddressDto;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final CompanyRepository companyRepository;
    private final AddressRepository repository;

    public List<AddressDto> findAll() {
        List<Address> list = repository.findAll();
        return list.stream().map(e -> new AddressDto(e)).collect(Collectors.toList());
    }

    public AddressDto findById(Integer id){
        Optional<Address> address = repository.findById(id);
        return new AddressDto(address.orElseThrow(()
                -> new ResourceNotFoundException(String.format("Address id %s not found", id))));
    }

    @Transactional
    public AddressDto save(Integer companyId, AddressDto dto){
        try{
            Company company = companyRepository.getOne(companyId);
            Address address = dto.toEntity();
            address.setCompany(company);
            return new AddressDto(repository.save(address));
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(String.format("Company id %s not found", companyId));
        }
    }

    @Transactional
    public AddressDto update(Integer companyId, AddressDto dto) {
        try{
            Company company = companyRepository.getOne(companyId);
            Address address = repository.getOne(company.getAddress().getId());
            updateData(address, dto);
            return new AddressDto(repository.save(address));
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(String.format("Company id %s not found", companyId));
        }
    }

    public void delete(Integer id) {
        try {
            Address address = repository.getOne(id);
            address.getCompany().setAddress(null);
            repository.deleteById(address.getId());
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(String.format("Address id %s not found", id));
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    private void updateData(Address address, AddressDto dto) {
        address.setStreet(dto.getStreet());
        address.setState(dto.getState());
        address.setNumber(dto.getNumber());
        address.setCity(dto.getCity());
    }
}
