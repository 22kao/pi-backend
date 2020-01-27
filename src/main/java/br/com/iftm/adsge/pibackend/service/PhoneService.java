package br.com.iftm.adsge.pibackend.service;

import br.com.iftm.adsge.pibackend.model.Company;
import br.com.iftm.adsge.pibackend.model.Phone;
import br.com.iftm.adsge.pibackend.model.dto.PhoneCompany;
import br.com.iftm.adsge.pibackend.repository.CompanyRepository;
import br.com.iftm.adsge.pibackend.repository.PhoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PhoneService {

    private final PhoneRepository repository;

    public List<PhoneCompany> findAll() {
        return null;
    }

    public PhoneCompany findById(Integer id) {
        return null;
    }

    public List<Phone> findAllByCompanyId(Integer companyId) {
        return repository.findAllByCompanyId(companyId);
    }

    //todo implementar
}
