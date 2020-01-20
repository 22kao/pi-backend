package br.com.iftm.adsge.pibackend.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cep;
    @NotBlank(message = "Address street cannot be blank")
    private String street;
    private String city;
    private String state;
    @NotNull(message = "Address number cannot be null")
    private Integer number;
    @OneToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;
}