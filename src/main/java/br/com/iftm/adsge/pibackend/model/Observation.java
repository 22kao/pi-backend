package br.com.iftm.adsge.pibackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public class Observation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Observation description cannot be null")
    private String description;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "user_id", nullable = false),
            @JoinColumn(name = "implantation_id", nullable = false)
    })
    private ModuleImplantation modulesImplantation;
}
