package br.com.iftm.adsge.pibackend.service;

import br.com.iftm.adsge.pibackend.model.Address;
import br.com.iftm.adsge.pibackend.model.Company;
import br.com.iftm.adsge.pibackend.model.dto.CompanyDTO;
import br.com.iftm.adsge.pibackend.model.dto.CompanyFullDTO;
import br.com.iftm.adsge.pibackend.repository.AddressRepository;
import br.com.iftm.adsge.pibackend.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository repository;
    private final AddressRepository addressRepository;

    public List<CompanyFullDTO> findAll() {
        List<Company> list = repository.findAll();
        return list.stream().map(e -> new CompanyFullDTO(e)).collect(Collectors.toList());
    }

    //todo testar um findid que não exista

    public CompanyFullDTO findById(Integer id){
        return new CompanyFullDTO(repository.findById(id).get());
    }

    public CompanyDTO save(CompanyFullDTO dto) {
        Company company = dto.toEntity();
        setCompanyAddress(company, dto.getAddress());
        repository.save(company);
        return new CompanyDTO(company);
    }

    private void setCompanyAddress(Company company, Address address) {
        if(address != null){
            address.setCompany(company);
            company.setAddress(address);
        }
    }

}
