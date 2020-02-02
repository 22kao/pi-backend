package br.com.iftm.adsge.pibackend.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Email(message = "Email should be valid")
    private String email;

    private String phone;

    private String password;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private Set<ImplantationModule> modulesImplantation = new HashSet<>();

    //todo relação authority
}