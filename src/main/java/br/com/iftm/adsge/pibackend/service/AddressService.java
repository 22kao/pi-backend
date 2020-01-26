package br.com.iftm.adsge.pibackend.service;

import br.com.iftm.adsge.pibackend.model.Address;
import br.com.iftm.adsge.pibackend.model.Company;
import br.com.iftm.adsge.pibackend.model.dto.AddressDTO;
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

    public List<AddressDTO> findAll() {
        List<Address> list = repository.findAll();
        return list.stream().map(e -> new AddressDTO(e)).collect(Collectors.toList());
    }

    public AddressDTO findById(Integer id){
        Optional<Address> address = repository.findById(id);
        return new AddressDTO(address.orElseThrow(() -> new ResourceNotFoundException(String.format("Address id %s not found", id))));
    }

    @Transactional
    public AddressDTO save(Integer companyId, AddressDTO dto){
        try{
            Company company = companyRepository.getOne(companyId);
            Address address = dto.toEntity();
            address.setCompany(company);
            return new AddressDTO(repository.save(address));
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(String.format("Company id %s not found", companyId));
        }
    }

    @Transactional
    public AddressDTO update(Integer companyId, AddressDTO dto) {
        try{
            Company company = companyRepository.getOne(companyId);
            Address address = repository.getOne(company.getAddress().getId());
            updateData(address, dto);
            return new AddressDTO(repository.save(address));
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

    private void updateData(Address address, AddressDTO dto) {
        address.setStreet(dto.getStreet());
        address.setState(dto.getState());
        address.setNumber(dto.getNumber());
        address.setCity(dto.getCity());
    }
}
