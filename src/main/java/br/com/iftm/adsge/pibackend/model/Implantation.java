package br.com.iftm.adsge.pibackend.model;

import br.com.iftm.adsge.pibackend.model.enums.ProgressStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Implantation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String description;

    private LocalDateTime dtExpectedInitial;

    private LocalDateTime dtExpected;

    @PastOrPresent(message = "Implantation realized date cannot be in the future")
    private LocalDateTime dtRealized;

    @PastOrPresent(message = "Implantation initial date cannot be in the future")
    private LocalDateTime dtInitial;

    @Enumerated(value = EnumType.STRING)
    private ProgressStatus status;

    @Transient
    @Builder.Default
    @OneToMany(mappedBy = "implantation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<ModuleImplantation> modulesImplantation = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @ToString.Exclude
    private Company company;

    public Implantation(String description, Company company, LocalDateTime dtExpected){
        this.description = description;
        this.status = ProgressStatus.IN_PROGRESS;
        this.dtInitial = LocalDateTime.now();
        this.company = company;
        this.dtExpected = dtExpected;
    }

    //todo alterar criaçaõ de progress status e dtinitial para o serviço de adição de uma nova implantação
    //todo acrescentar método para adicionar um módulo a esta implantação
    //todo quando o expected alterar a data, a data anterior será adicionada ao expectedInitial
}
