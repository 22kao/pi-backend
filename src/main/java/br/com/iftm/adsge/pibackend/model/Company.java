package br.com.iftm.adsge.pibackend.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
    private String cnpj;
    @Email(message = "Email should be valid")
    private String email;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private Set<Phone> phones;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToOne(mappedBy = "company", cascade = CascadeType.ALL)
    private Address address;
    @OneToOne(mappedBy = "company")
    private Implantation implantation;

}
