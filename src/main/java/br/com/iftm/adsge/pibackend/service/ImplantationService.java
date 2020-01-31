package br.com.iftm.adsge.pibackend.service;

import br.com.iftm.adsge.pibackend.model.Company;
import br.com.iftm.adsge.pibackend.model.Implantation;
import br.com.iftm.adsge.pibackend.model.dto.ImplantationDto;
import br.com.iftm.adsge.pibackend.repository.CompanyRepository;
import br.com.iftm.adsge.pibackend.repository.ImplantationRepository;
import br.com.iftm.adsge.pibackend.service.exceptions.DatabaseException;
import br.com.iftm.adsge.pibackend.service.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImplantationService {

    private final ImplantationRepository repository;
    private final CompanyRepository companyRepository;

    public List<ImplantationDto> findAll() {
        List<Implantation> list = repository.findAll();
        return list.stream().map(e -> new ImplantationDto(e)).collect(Collectors.toList());
    }

    public ImplantationDto findById(Long id) {
        Implantation implantation = repository.getOne(id);
        return new ImplantationDto(implantation);
    }

    public List<ImplantationDto> findAllByCompanyId(Integer companyId){
        List<Implantation> list = repository.findAllByCompanyId(companyId);
        return list.stream().map(e -> new ImplantationDto(e)).collect(Collectors.toList());
    }

    public ImplantationDto save(Integer companyId, ImplantationDto implantationDto) {
        try {
            Company company = companyRepository.getOne(companyId);
            Implantation implantation = new Implantation(implantationDto.getDescription(), company);
            implantation.setDtExpected(implantationDto.getDtExpected());
            return new ImplantationDto(repository.save(implantation));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(String.format("Company id %s not found", companyId));
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(String.format("Implantation id %s not found", id));
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public ImplantationDto update(Long id, ImplantationDto implantationDto) {
        try{
            Implantation implantation = repository.getOne(id);
            updateData(implantation, implantationDto);
            return new ImplantationDto(repository.save(implantation));
        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException(String.format("Implantation id %s not found", id));
        }
    }

    private void updateData(Implantation impl, ImplantationDto dto) {
        impl.setDescription(dto.getDescription());
        impl.setDtRealized(dto.getDtRealized());
        impl.setStatus(dto.getStatus());
        impl.setDtInitial(dto.getDtInitial());
        //ExpectedInitialDate can't be manual changed
        if(impl.getDtExpectedInitial() == null && !Objects.equals(dto.getDtExpected(), impl.getDtExpected()))
            impl.setDtExpectedInitial(impl.getDtExpected());
        impl.setDtExpected(dto.getDtExpected());
    }
}
