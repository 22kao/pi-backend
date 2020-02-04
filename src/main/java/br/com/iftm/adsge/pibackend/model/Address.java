package br.com.iftm.adsge.pibackend.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Integer id;
    private String cep;
    private String street;
    private String city;
    private String state;
    private Integer number;

    @OneToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Company company;
}