package br.com.iftm.adsge.pibackend.config;

import br.com.iftm.adsge.pibackend.model.*;
import br.com.iftm.adsge.pibackend.model.Module;
import br.com.iftm.adsge.pibackend.model.enums.ProgressStatus;
import br.com.iftm.adsge.pibackend.model.pk.ModuleImplantationPk;
import br.com.iftm.adsge.pibackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private ImplantationRepository implantationRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private ObservationRepository observationRepository;

    @Autowired
    private ModuleImplantationRepository moduleImplantationRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {

        //todo create exception handler for Caused by: javax.validation.ConstraintViolationException:

        //User
        User u1 = new User();
        u1.setEmail("teste@gmail.com");
        u1.setName("Ricardo");
        u1.setPassword("4545");
        u1.setPhone("34988689945");

        userRepository.save(u1);

        //Address
        Address ad1 = Address.builder()
                .number(1545)
                .street("Street Maria")
                .city("Jerusalem").build();

        //Phones
        Phone p1 = new Phone();
        p1.setPhone("34997788955");
        p1.setUsername("Alita");

        Phone p2 = new Phone();
        p2.setPhone("34998986655");
        p2.setUsername("Richard");

        //Company
        Company c1 = Company.builder().address(ad1)
                .phones(new HashSet<>(Arrays.asList(p1, p2)))
                .cnpj("1111111111")
                .email("teste@gmail.com")
                .name("Zequinha games").build();

        ad1.setCompany(c1);
        p1.setCompany(c1);
        p2.setCompany(c1);

        companyRepository.save(c1);

        //Phone
        Phone p3 = new Phone();
        p3.setPhone("34988542245");
        p3.setUsername("Joseph");
        phoneRepository.save(p3);

        //Address
        Address ad2 = Address.builder()
                .cep("38408566")
                .city("Boston")
                .company(c1)
                .number(5462)
                .state("Massachusetts")
                .street("Random street").build();
        addressRepository.save(ad2);

        //Implantation
        Implantation imp1 = Implantation.builder()
                .company(c1)
                .description("First Implantation")
                .status(ProgressStatus.IN_PROGRESS).build();
        Implantation imp2 = Implantation.builder()
                .company(c1)
                .description("Second Implantation")
                .status(ProgressStatus.CONCLUDED)
                .dtExpected(LocalDateTime.parse("2019-10-25 15:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .dtExpectedInitial(LocalDateTime.parse("2019-10-10 15:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .dtRealized(LocalDateTime.parse("2020-01-05 15:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .dtInitial(LocalDateTime.parse("2019-05-25 15:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
        implantationRepository.saveAll(Arrays.asList(imp1, imp2));

        //Module
        Module m1 = new Module();
        m1.setDescription("Module Financial");

        //Observation
        Observation o1 = new Observation();
        o1.setDescription("Observation 1");
        Observation o2 = new Observation();
        o1.setDescription("Observation 2");

        //ImplantationModules
        ModuleImplantation mi1 = ModuleImplantation.builder().implantation(imp1).user(u1).score(10).status(ProgressStatus.IN_PROGRESS)
                //.id(mpk)
                .dtInitial(LocalDateTime.parse("2019-05-25 15:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .module(m1)
                .observations(Arrays.asList(o1,o2))
                .pendency("Pendencia")
                .userResponsible("Alexia")
                .build();

        moduleImplantationRepository.save(mi1);

    }
}
