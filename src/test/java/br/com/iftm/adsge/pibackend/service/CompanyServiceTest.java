package br.com.iftm.adsge.pibackend.service;

import br.com.iftm.adsge.pibackend.model.Company;
import br.com.iftm.adsge.pibackend.model.dto.CompanyFullDTO;
import br.com.iftm.adsge.pibackend.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    @Mock
    private CompanyRepository repository;

    @InjectMocks
    private CompanyService service;

    private CompanyFullDTO company;

    @BeforeEach
    void setUp(){
        company = new CompanyFullDTO(Company.builder().document("123456789").build());
    }

    @Test
    void savedCompanyHasName(){
        when(repository.save(any(Company.class))).thenReturn(company.toEntity());
        CompanyFullDTO savedCompany = service.save(company);
        assertThat(savedCompany.getName()).isNotNull();
    }

    @Test
    void savedCompanyHasDocument(){
        when(repository.save(any(Company.class))).thenReturn(company.toEntity());
        CompanyFullDTO savedCompany = service.save(company);
        assertThat(savedCompany.getDocument()).isNotNull();
    }


}
