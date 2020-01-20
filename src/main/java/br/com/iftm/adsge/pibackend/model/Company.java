package br.com.iftm.adsge.pibackend.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
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

    @NotBlank(message = "Company cnpj cannot be blank")
    private String cnpj;

    @Email(message = "Email should be valid")
    private String email;

    @Builder.Default
    @Transient
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private Set<Phone> phones = new HashSet<>();

    @Transient
    @ToString.Exclude
    @OneToOne(mappedBy = "company", cascade = CascadeType.ALL)
    private Address address;

    @OneToOne(mappedBy = "company")
    private Implantation implantation;

    public Company(String name, String cnpj){
        this.name = name;
        this.cnpj = cnpj;
    }
}
