package br.com.iftm.adsge.pibackend.config;

import br.com.iftm.adsge.pibackend.model.*;
import br.com.iftm.adsge.pibackend.model.Module;
import br.com.iftm.adsge.pibackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
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
    private ImplantationModuleRepository moduleImplantationRepository;

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
        Address ad1 = new Address("DuqCity", "Street Duq", 145, c1);
        Address ad2 = new Address("JoaCity", "Street Joa", 1653, c3);
        Address ad3 = new Address("CatCity", "Street Cat", 452, c2);
        Address ad4 = new Address("ArlCity", "Street Arl", 985, c4);
        Address ad5 = new Address("GomCity", "Street Gom", 1201, c5);

        addressRepository.saveAll(Arrays.asList(ad1, ad2, ad3, ad4, ad5));

        //Phones
        Phone p2 = new Phone(null, "34985562233", "Edward", c1);
        Phone p1 = new Phone(null, "34997788955", "Josh", c2);
        Phone p3 = new Phone(null, "34954565333", "Alphonse", c3);
        Phone p4 = new Phone(null, "34948785511", "Kile", c1);
        Phone p5 = new Phone(null, "34969852234", "Gon", c4);
        //todo testar criação de telefone sem empresa
        Phone p6 = new Phone(null, "34923214455", "Kur", c5);

        phoneRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6));

        //Implantation
        Implantation imp1 = new Implantation("Implantation 1", c1);
        Implantation imp2 = new Implantation("Implantation 2", c2);
        Implantation imp3 = new Implantation("Implantation 3", c3);
        Implantation imp4 = new Implantation("Implantation 4", c1);

        implantationRepository.saveAll(Arrays.asList(imp1, imp2, imp3, imp4));

        //Modules
        Module m1 = new Module("Financial Module");
        Module m2 = new Module("Fiscal Module");
        Module m3 = new Module("Deposit Module");

        moduleRepository.saveAll(Arrays.asList(m1, m2, m3));

        //Modules Implantation
        ImplantationModule mi1 = new ImplantationModule(u1, imp1, m1);
        ImplantationModule mi2 = new ImplantationModule(u2, imp2, m2);
        ImplantationModule mi3 = new ImplantationModule(u1, imp2, m3);
        ImplantationModule mi4 = new ImplantationModule(u1, imp2, m1);
        ImplantationModule mi5 = new ImplantationModule(u1, imp2, m2);
        ImplantationModule mi6 = new ImplantationModule(u1, imp1, m3);
        ImplantationModule mi7 = new ImplantationModule(u1, imp1, m2);
        ImplantationModule mi8 = new ImplantationModule(u2, imp1, m1);
        ImplantationModule mi9 = new ImplantationModule(u2, imp2, m3);

        imp1.setModulesImplantation(new HashSet<>(Arrays.asList(mi1, mi6, mi7, mi8)));
        imp2.setModulesImplantation(new HashSet<>(Arrays.asList(mi2, mi3, mi4, mi5, mi9)));
        moduleImplantationRepository.saveAll(Arrays.asList(mi1, mi2, mi3, mi4, mi5, mi6, mi7, mi8, mi9));

        //Observations
        Observation ob1 = Observation.builder()
                .description("First observation")
                .implantationModule(mi1)
                .user(u1).build();
        Observation ob2 = Observation.builder()
                .description("Second observation")
                .implantationModule(mi1)
                .user(u1).build();
        Observation ob3 = Observation.builder()
                .description("Third observation")
                .implantationModule(mi2)
                .user(u2).build();
        Observation ob4 = Observation.builder()
                .description("Fourth observation")
                .implantationModule(mi2)
                .user(u1).build();
        Observation ob5 = Observation.builder()
                .description("Fifth observation")
                .implantationModule(mi3)
                .user(u2).build();

        observationRepository.saveAll(Arrays.asList(ob1, ob2, ob3, ob4, ob5));
    }
}
