package br.com.iftm.adsge.pibackend.model;

import br.com.iftm.adsge.pibackend.model.enums.ProgressStatus;
import br.com.iftm.adsge.pibackend.model.pk.ModuleImplantationPk;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModuleImplantation {
    @EmbeddedId
    private ModuleImplantationPk id;
    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @MapsId("implantation_id")
    @JoinColumn(name = "implantation_id")
    private Implantation implantation;

    @Enumerated(value = EnumType.STRING)
    private ProgressStatus status;
    @PastOrPresent(message = "Module Implantation initial date cannot be in the future")
    private LocalDateTime dtInitial;
    @PastOrPresent(message = "Module Implantation initial date cannot be in the future")
    private LocalDateTime dtEnd;
    private String pendency;
    private Integer score;
    private String userResponsible;
    @OneToMany(mappedBy = "modulesImplantation")
    private List<Observation> observations;
    @NotNull(message = "Module Implantation module cannot be null")
    @ManyToOne
    @JoinColumn(name = "module_id",referencedColumnName = "id", nullable = false)
    private Module module;
}
