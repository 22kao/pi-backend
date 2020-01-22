package br.com.iftm.adsge.pibackend.model;

import br.com.iftm.adsge.pibackend.model.enums.ProgressStatus;
import br.com.iftm.adsge.pibackend.model.compositekey.ModuleImplantationId;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModuleImplantation {

    @EmbeddedId
    private ModuleImplantationId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("implantationId")
    private Implantation implantation;

    @ManyToOne
    @JoinColumn(name = "module_id", referencedColumnName = "id", nullable = false)
    private Module module;

    @Enumerated(value = EnumType.STRING)
    private ProgressStatus status;

    @PastOrPresent(message = "Module Implantation initial date cannot be in the future")
    private LocalDateTime dtInitial;

    @PastOrPresent(message = "Module Implantation initial date cannot be in the future")
    private LocalDateTime dtEnd;

    private String pendent;

    private Integer score;

    private String userResponsible;

    @OneToMany(mappedBy = "moduleImplantation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Observation> observations;

    public ModuleImplantation(User user, Implantation implantation, Module module) {
        this.dtInitial = LocalDateTime.now();
        this.status = ProgressStatus.IN_PROGRESS;
        this.user = user;
        this.implantation = implantation;
        this.module = module;
        this.id = new ModuleImplantationId(user.getId(), implantation.getId());
    }
}
