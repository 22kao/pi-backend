package br.com.iftm.adsge.pibackend.service;

import br.com.iftm.adsge.pibackend.model.Address;
import br.com.iftm.adsge.pibackend.model.Company;
import br.com.iftm.adsge.pibackend.model.Phone;
import br.com.iftm.adsge.pibackend.model.dto.CompanyDto;
import br.com.iftm.adsge.pibackend.repository.AddressRepository;
import br.com.iftm.adsge.pibackend.repository.CompanyRepository;
import br.com.iftm.adsge.pibackend.service.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    @Mock
    private CompanyRepository repository;
    @Mock
    private AddressRepository addressRepository;
    @InjectMocks
    private CompanyService service;


    @BeforeEach
    void setUp() {
    }

    @Test
    void savedCompanyAndCompanyDetailed_hasNameAndDocumentEquals() {
        CompanyDto detailed = new CompanyDto();
        detailed.setName("Company Sample");
        detailed.setDocument("123456");

        // mock
        Company company = Company.builder()
                .name(detailed.getName())
                .document(detailed.getDocument()).build();
        when(repository.save(any(Company.class))).thenReturn(company);

        //When
        CompanyDto created = service.save(detailed);

        //then
        assertThat(created.getName()).isEqualTo(detailed.getName());
        assertThat(created.getDocument()).isEqualTo(detailed.getDocument());
    }

    @Test
    void savedCompanyAndCompanyDetailed_hasAddressEquals() {
        CompanyDto detailed = new CompanyDto();
        detailed.setAddress(Address.builder().city("GomCity").street("Street Gom").number(1201).build());

        // Mock
        Company company = Company.builder()
                .address(detailed.getAddress()).build();

        when(repository.save(any(Company.class))).thenReturn(company);

        CompanyDto created = service.save(detailed);

        assertThat(created.getAddress().getCity()).isEqualTo(detailed.getAddress().getCity());
        assertThat(created.getAddress().getStreet()).isEqualTo(detailed.getAddress().getStreet());
        assertThat(created.getAddress().getNumber()).isEqualTo(detailed.getAddress().getNumber());
    }

    @Test
    void savedCompanyAndCompanyDetailed_hasPhonesEquals() {
        CompanyDto detailed = new CompanyDto();
        Phone p1 = new Phone(null, "34978785454", "Username1", null);
        Phone p2 = new Phone(null, "34976485423", "Username2", null);
        detailed.setPhones(Arrays.asList(p1,p2));

        // Mock
        Company company = Company.builder()
                .phones(detailed.getPhones()).build();

        when(repository.save(any(Company.class))).thenReturn(company);

        CompanyDto created = service.save(detailed);

        assertThat(created.getPhones().contains(detailed.getPhones()));
        assertThat(detailed.getPhones().contains(created.getPhones()));
    }

    @Test
    void whenFindAll_ShouldReturnList(){
        CompanyDto detailed = new CompanyDto();
        List<CompanyDto> detailedList = Arrays.asList(detailed);

        //Mock
        List<Company> companies = new ArrayList<>();
        for(CompanyDto cd : detailedList)
            companies.add(cd.toEntity());

        when(repository.findAll()).thenReturn(companies);

        List<CompanyDto> created = service.findAll();

        assertThat(created).isNotEmpty();
        assertThat(detailedList.size()).isEqualTo(created.size());

    }

    @Test
    void whenFindByIdValid_ShouldReturnCompany(){
        Integer id = 01;

        //Mock
        Company company = Company.builder().id(id).name("Test").document("123").build();
        Optional<Company> optCompany = Optional.ofNullable(company);
        when(repository.findById(anyInt())).thenReturn(optCompany);

        CompanyDto created = service.findById(id);

        assertThat(created.getId()).isEqualTo(id);
        assertThat(created.getId()).isPositive();
    }

    @Test
    void whenFindByIdInvalid_ShouldThrowException(){
        Integer id = -1;

        //Mock
        Optional<Company> optCompany = Optional.ofNullable(null);
        when(repository.findById(anyInt())).thenReturn(optCompany);

        Exception exception = assertThrows(ResourceNotFoundException.class,
                () -> { CompanyDto created = service.findById(id); } );

        assertThat(exception.getMessage()).contains(id.toString());
        assertThat(exception.getClass()).isEqualTo(ResourceNotFoundException.class);
    }

    /*@Test
    void whenUpdateCompany_withValidId_ReturnUpdatedCompany(){
        Integer id = 1;

        CompanyDetailed detailed = new CompanyDetailed();
        detailed.setName("New name");
        detailed.setDocument("New document");
        detailed.setAddress(Address.builder().city("New city").build());
        detailed.setPhones(Arrays.asList(new Phone(null,"","New Phone", null)));

        //Mock
        Company company = Company.builder()
                .name(detailed.getName())
                .document(detailed.getDocument())
                .address(detailed.getAddress())
                .phones(detailed.getPhones())
                .build();
        when(repository.getOne(anyInt())).thenReturn(company);
        lenient().when(addressRepository.getOne(anyInt())).thenReturn(Address.builder().id(1).build());
        when(repository.save(any(Company.class))).thenReturn(company);

        CompanyDetailed created = service.update(id, detailed);

        assertThat(created.getName()).isEqualTo(detailed.getName());
        assertThat(created.getDocument()).isEqualTo(detailed.getDocument());
        assertThat(created.getAddress()).isEqualTo(detailed.getAddress());
        assertThat(created.getPhones().contains(detailed.getPhones()));
        assertThat(detailed.getPhones().contains(created.getPhones()));
    }*/
}
