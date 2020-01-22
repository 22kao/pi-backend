package br.com.iftm.adsge.pibackend.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @NotBlank(message = "Company name cannot be blank")
    @EqualsAndHashCode.Include
    private String name;

    @NotBlank(message = "Company document cannot be blank")
    @EqualsAndHashCode.Include
    private String document;

    @Email(message = "Email should be valid")
    private String email;

    @ToString.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Phone> phones = new ArrayList<>();


    @ToString.Exclude
    @OneToOne(mappedBy = "company", cascade = CascadeType.ALL)
    private Address address;

    @ToString.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Implantation> implantation = new HashSet<>();

    public Company(String name, String document){
        this.name = name;
        this.document = document;
    }
}
