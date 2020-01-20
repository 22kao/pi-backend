package br.com.iftm.adsge.pibackend.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Phone cannot be blank")
    private String phone;
    private String username;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}