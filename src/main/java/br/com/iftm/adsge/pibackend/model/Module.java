package br.com.iftm.adsge.pibackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Module description cannot be blank")
    private String description;

    @OneToMany(mappedBy = "module")
    private List<ModuleImplantation> modulesImplantation;
}
