package br.com.iftm.adsge.pibackend.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;
    private String phone;
    private String username;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Company company;
}
