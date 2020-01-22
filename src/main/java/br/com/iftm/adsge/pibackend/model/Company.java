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
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Company name cannot be blank")
    private String name;

    @NotBlank(message = "Company document cannot be blank")
    private String document;

    @Email(message = "Email should be valid")
    private String email;

    @Builder.Default
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Phone> phones = new ArrayList<>();

    @OneToOne(mappedBy = "company", cascade = CascadeType.ALL)
    private Address address;

    @Builder.Default
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private Set<Implantation> implantations = new HashSet<>();

    public Company(String name, String document){
        this.name = name;
        this.document = document;
    }
}
