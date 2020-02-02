package br.com.iftm.adsge.pibackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Observation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Observation description cannot be null")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "implantation_module_id")
    private ImplantationModule implantationModule;

    public Observation(String description, ImplantationModule module){
        this.description = description;
        this.implantationModule = module;
    }
}
