package br.com.iftm.adsge.pibackend.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Email(message = "Email should be valid")
    private String email;
    private String phone;
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<ModuleImplantation> modulesImplantation;

    //todo relação authority
}