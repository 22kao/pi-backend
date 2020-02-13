package br.com.iftm.adsge.pibackend.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @EqualsAndHashCode.Include
    private String name;

    @EqualsAndHashCode.Include
    private String document;

    private String email;

    @ToString.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "company",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<Phone> phones = new ArrayList<>();

    @ToString.Exclude
    @OneToOne(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;

    @ToString.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private Set<Implantation> implantation = new HashSet<>();

    public Company(String name, String document){
        this.name = name;
        this.document = document;
    }
}
