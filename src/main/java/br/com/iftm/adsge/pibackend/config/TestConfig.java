package br.com.iftm.adsge.pibackend.config;

import br.com.iftm.adsge.pibackend.model.*;
import br.com.iftm.adsge.pibackend.model.Module;
import br.com.iftm.adsge.pibackend.model.enums.ProgressStatus;
import br.com.iftm.adsge.pibackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;

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

        //todo
        /* classe ModuleImplantation
        - pendency é necessário? tendo em vista a tabela de observação
        - status é um enum

        classe Observation
        - adição de data da observação
        - alteração de string do usuário para a classe User*/

        //Users
        User u1 = User.builder()
                .email("teste@gmail.com")
                .name("Alexia")
                .password("xxx5xxx")
                .phone("34988689945").build();

        User u2 = User.builder()
                .email("teste_dois@gmail.com")
                .name("Richard")
                .password("Xxx5xxX")
                .phone("34986453322").build();

        userRepository.saveAll(Arrays.asList(u1, u2));

        //Company
        Company c1 = new Company("Apple", "12345678910111");
        Company c2 = new Company("Amazon", "12345678910122");
        Company c3 = new Company("Sun", "12345678910133");
        Company c4 = new Company("UnitedHealth Group", "12345678910144");
        Company c5 = new Company("Burger King", "12345678910155");

        companyRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5));

        //Address
        //Address ad1 = new Address("Street Cax", 451, c1);
        Address ad2 = new Address("Street Duq", 145, c1);
        Address ad3 = new Address("Street Joa", 1653, c3);
        Address ad4 = new Address("Street Cat", 452, c2);
        Address ad5 = new Address("Street Arl", 985, c4);
        Address ad6 = new Address("Street Gom", 1201, c5);

        addressRepository.saveAll(Arrays.asList(/*ad1, */ad2, ad3, ad4, ad5, ad6));

        //Phones
        Phone p2 = new Phone(null, "34985562233", "Edward", c1);
        Phone p1 = new Phone(null, "34997788955", "Josh", c2);
        Phone p3 = new Phone(null, "34954565333", "Alphonse", c3);
        Phone p4 = new Phone(null, "34948785511", "Kile", c1);
        Phone p5 = new Phone(null, "34969852234", "Gon", c4);
        Phone p6 = new Phone(null, "34923214455", "Kur", c5);

        phoneRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6));

        //Implantation
        LocalDateTime expectedDate = LocalDateTime.of(2020, Month.JULY, 25, 15, 10);
        Implantation imp1 = new Implantation("Implantation 1", c1, expectedDate);
        Implantation imp2 = new Implantation("Implantation 1", c2, expectedDate.plusMonths(2));
        Implantation imp3 = new Implantation("Implantation 1", c3, expectedDate.plusDays(20));
        Implantation imp4 = new Implantation("Implantation 2", c1, expectedDate.plusYears(1));

        implantationRepository.saveAll(Arrays.asList(imp1, imp2, imp3, imp4));

        //Modules
        Module m1 = new Module("Financial Module");
        Module m2 = new Module("Fiscal Module");
        Module m3 = new Module("Deposit Module");

        moduleRepository.saveAll(Arrays.asList(m1, m2, m3));

        //Modules Implantation
        ModuleImplantation mi1 = new ModuleImplantation(u1, imp1, m1);
        ModuleImplantation mi2 = new ModuleImplantation(u2, imp2, m2);
        ModuleImplantation mi3 = new ModuleImplantation(u1, imp2, m3);

        moduleImplantationRepository.saveAll(Arrays.asList(mi1, mi2, mi3));

        //Observations
        Observation ob1 = new Observation("Observation 1", mi1);
        Observation ob2 = new Observation("Observation 2", mi1);
        Observation ob3 = new Observation("Observation 3", mi2);
        Observation ob4 = new Observation("Observation 4", mi2);
        Observation ob5 = new Observation("Observation 5", mi3);

        observationRepository.saveAll(Arrays.asList(ob1, ob2, ob3, ob4, ob5));
    }
}
