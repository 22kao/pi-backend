package br.com.iftm.adsge.pibackend.service;

import br.com.iftm.adsge.pibackend.model.Phone;
import br.com.iftm.adsge.pibackend.model.dto.PhoneDto;
import br.com.iftm.adsge.pibackend.repository.PhoneRepository;
import br.com.iftm.adsge.pibackend.service.exceptions.DatabaseException;
import br.com.iftm.adsge.pibackend.service.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhoneService {

    private final PhoneRepository repository;

    public List<PhoneDto> findAll() {
        List<Phone> list = repository.findAll();
        return list.stream().map(e -> new PhoneDto(e)).collect(Collectors.toList());
    }

    public PhoneDto findById(Long id) {
        Optional<Phone> obj = repository.findById(id);
        Phone phone = obj.orElseThrow(() ->
                new ResourceNotFoundException(String.format("Company id %s not found", id)));
        return new PhoneDto(phone);
    }

    @Transactional
    public PhoneDto update(Long id, PhoneDto dto) {
        Phone phone = repository.getOne(id);
        phone.setPhone(dto.getPhone());
        phone.setUsername(dto.getUsername());
        return new PhoneDto(repository.save(phone));
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
