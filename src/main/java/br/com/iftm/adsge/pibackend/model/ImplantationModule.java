package br.com.iftm.adsge.pibackend.model;

import br.com.iftm.adsge.pibackend.model.enums.ProgressStatus;
import br.com.iftm.adsge.pibackend.model.compositekey.ModuleImplantationId;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class ImplantationModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private ProgressStatus status;

    private LocalDateTime dtInitial;

    private LocalDateTime dtEnd;

    private Integer score;

    private String userResponsible;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id", referencedColumnName = "id", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Module module;

    @OneToMany(mappedBy = "implantationModule", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Observation> observations;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "implantation_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Implantation implantation;

    public ImplantationModule(User user, Implantation implantation, Module module) {
        this.dtInitial = LocalDateTime.now();
        this.status = ProgressStatus.IN_PROGRESS;
        this.user = user;
        this.implantation = implantation;
        this.module = module;
    }
}
