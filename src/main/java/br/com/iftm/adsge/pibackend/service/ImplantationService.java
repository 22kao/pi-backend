package br.com.iftm.adsge.pibackend.service;

import br.com.iftm.adsge.pibackend.model.Company;
import br.com.iftm.adsge.pibackend.model.Implantation;
import br.com.iftm.adsge.pibackend.model.User;
import br.com.iftm.adsge.pibackend.model.dto.RequestImplantation;
import br.com.iftm.adsge.pibackend.model.dto.ResponseImplantation;
import br.com.iftm.adsge.pibackend.repository.CompanyRepository;
import br.com.iftm.adsge.pibackend.repository.ImplantationRepository;
import br.com.iftm.adsge.pibackend.service.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImplantationService {

    private final ImplantationRepository repository;
    private final CompanyRepository companyRepository;

    public List<ResponseImplantation> findAll() {
        List<Implantation> list = repository.findAll();
        return list.stream().map(e -> new ResponseImplantation(e)).collect(Collectors.toList());
    }

    public ResponseImplantation findById(Long id) {
        Implantation implantation = repository.getOne(id);
        return new ResponseImplantation(implantation);
    }

    public List<ResponseImplantation> findAllByCompanyId(Integer companyId){
        List<Implantation> list = repository.findAllByCompanyId(companyId);
        return list.stream().map(e -> new ResponseImplantation(e)).collect(Collectors.toList());
    }

    public ResponseImplantation save(Integer companyId, RequestImplantation requestImp) {
        try {
            Company company = companyRepository.getOne(companyId);
            Implantation implantation = new Implantation(requestImp.getDescription(), company);
            implantation.setDtExpected(requestImp.getDtExpected());
            return new ResponseImplantation(repository.save(implantation));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(String.format("Company id %s not found", companyId));
        }
    }
}
