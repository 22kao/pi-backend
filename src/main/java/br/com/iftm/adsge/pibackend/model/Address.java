package br.com.iftm.adsge.pibackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @NotBlank(message = "Address city cannot be blank")
    private String city;

    private String state;

    @NotNull(message = "Address number cannot be null")
    private Integer number;

    @OneToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Company company;

    public Address(String city, String street, Integer number, Company company){
        this.city = city;
        this.street = street;
        this.number = number;
        this.company = company;
    }
}