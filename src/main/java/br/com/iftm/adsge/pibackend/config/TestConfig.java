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

        Company c1 = Company.builder()
                .name("Apple")
                .document("12345678910111")
                .email("apple@apple.com").build();
        Company c2 = Company.builder()
                .name("Amazon")
                .document("12345678910122")
                .email("amazon@amazon.com").build();
        Company c3 = Company.builder()
                .name("Sun")
                .document("12345678910133")
                .email("sun@oracle.com").build();
        Company c4 = Company.builder()
                .name("UnitedHealth Group")
                .document("12345678910144")
                .email("ugh@ugh.com").build();
        Company c5 = Company.builder()
                .name("Burger King")
                .document("12345678910155")
                .email("bk@bk.com").build();

        companyRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5));

        //Address
        Address ad1 = Address.builder().street("Street Duq").number(145).company(c1).build();
        Address ad2 = Address.builder().street("Street Joa").number(1653).company(c3).build();
        Address ad3 = Address.builder().street("Street Cat").number(452).company(c2).build();
        Address ad4 = Address.builder().street("Street Arl").number(985).company(c4).build();
        Address ad5 = Address.builder().street("Street Gom").number(1201).company(c5).build();

        addressRepository.saveAll(Arrays.asList(ad1, ad2, ad3, ad4, ad5));

        //Phones
        Phone p2 = new Phone(null, "34985562233", "Edward", c1);
        Phone p1 = new Phone(null, "34997788955", "Josh", c2);
        Phone p3 = new Phone(null, "34954565333", "Alphonse", c3);
        Phone p4 = new Phone(null, "34948785511", "Kile", c1);
        Phone p5 = new Phone(null, "34969852234", "Gon", c4);
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
