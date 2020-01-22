package br.com.iftm.adsge.pibackend.repository;

import br.com.iftm.adsge.pibackend.model.Company;
import br.com.iftm.adsge.pibackend.model.dto.CompanyDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class CompanyRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CompanyRepository repository;

    private List<Company> companyList = new ArrayList<>();

    @BeforeEach
    void setUp(){
        companyList.add(new Company("Apple","12345678912124"));
        companyList.add(new Company("TQI","12345678913231"));
        companyList.add(new Company("Mining Triangle Federal University","12345678910121"));
    }

    @Test
    void whenFindAll_NamesAreNotNull(){
        for(Company company : companyList){
            entityManager.persist(company);
            entityManager.flush();
        }

        List<Company> list = repository.findAll();

        for(Company c : list)
            assertThat(c.getName()).isNotNull();
    }
}
