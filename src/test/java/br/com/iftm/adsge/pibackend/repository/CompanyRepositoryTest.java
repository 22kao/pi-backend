package br.com.iftm.adsge.pibackend.repository;

import br.com.iftm.adsge.pibackend.model.Address;
import br.com.iftm.adsge.pibackend.model.Company;
import br.com.iftm.adsge.pibackend.model.Phone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.core.parameters.P;

import javax.persistence.EntityNotFoundException;
import javax.persistence.Table;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class CompanyRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CompanyRepository repository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    private List<Company> companyList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        companyList.add(new Company("Apple", "12345678912124"));
        companyList.add(new Company("TQI", "12345678913231"));
        companyList.add(new Company("Mining Triangle Federal University", "12345678910121"));
    }

    @Test
    void whenSaveCompany_withEmailInvalid_shouldThrowConstraintViolationException() {
        Company company = Company.builder()
                .name("Company1")
                .document("123345678912134")
                .email("test_invalid_email").build();

        Exception exception = assertThrows(ConstraintViolationException.class,
                () -> entityManager.persist(company));

        assertThat(exception.getMessage().contains("email"));
    }

    @Test
    void whenSaveCompany_withNameNull_shouldThrowConstraintViolationException() {
        Company company = Company.builder()
                .name(null)
                .document("123345678912134")
                .email("test_valid_email@test.com").build();

        Exception exception = assertThrows(ConstraintViolationException.class,
                () -> entityManager.persist(company));

        assertThat(exception.getMessage().contains("name"));
    }

    @Test
    void whenSaveCompany_withDocumentNull_shouldThrowConstraintViolationException() {
        Company company = Company.builder()
                .name("Company1")
                .document(null)
                .email("test_valid_email@test.com").build();

        Exception exception = assertThrows(ConstraintViolationException.class,
                () -> entityManager.persist(company));

        assertThat(exception.getMessage().contains("email"));
    }

    @Test
    void whenFindAllCompanies_namesAndDocumentsCannotBeNull() {
        for (Company company : companyList) {
            entityManager.persist(company);
            entityManager.flush();
        }

        List<Company> list = repository.findAll();

        for (Company c : list){
            assertThat(c.getName()).isNotNull();
            assertThat(c.getDocument()).isNotNull();
        }
    }

    @Test
    void companyAddressAndSavedAddress_ShouldBeEquals(){
        Company company = Company.builder()
                .name("Company1")
                .document("12245678991245")
                .email("test_valid_email@test.com").build();

        Address companyAddress = Address.builder()
                .number(125)
                .city("City 1")
                .street("Street 1")
                .company(company).build();

        company.setAddress(companyAddress);
        entityManager.persist(company);
        entityManager.flush();

        Optional<Address> obj = addressRepository.findById(companyAddress.getId());
        Address savedAddress = obj.orElseThrow(() -> new EntityNotFoundException());

        assertThat(company).isEqualTo(savedAddress.getCompany());
        assertThat(company.getAddress().getNumber()).isEqualTo(savedAddress.getNumber());
        assertThat(company.getAddress().getCity()).isEqualTo(savedAddress.getCity());
        assertThat(company.getAddress().getStreet()).isEqualTo(savedAddress.getStreet());
    }

    @Test
    void companyPhoneAndSavedPhones_ShouldBeEquals(){
        Company company = Company.builder()
                .name("Company1")
                .document("12245678991245")
                .email("test_valid_email@test.com").build();

        Phone phone1 = new Phone(null, "34985525656", "User1", company);
        Phone phone2 = new Phone(null, "34975757575", "User2", company);

        company.setPhones(Arrays.asList(phone1, phone2));

        entityManager.persist(company);
        entityManager.flush();

        List<Phone> phones = phoneRepository.findAll();

        for(Phone phone : phones){
            assertThat(phone.getCompany()).isEqualTo(company);
        }
        assertThat(phones.contains(company.getPhones()));
        assertThat(company.getPhones().contains(phones));
    }
}
