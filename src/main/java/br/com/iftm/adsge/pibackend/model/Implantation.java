package br.com.iftm.adsge.pibackend.model;

import br.com.iftm.adsge.pibackend.model.enums.ProgressStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Implantation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    private String description;

    private LocalDateTime dtExpectedInitial;

    private LocalDateTime dtExpected;

    private LocalDateTime dtRealized;

    private LocalDateTime dtInitial;

    @Enumerated(value = EnumType.STRING)
    private ProgressStatus status;

    @Builder.Default
    @OneToMany(mappedBy = "implantation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<ImplantationModule> modulesImplantation = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Company company;

    public Implantation(String description, Company company){
        this.description = description;
        this.status = ProgressStatus.IN_PROGRESS;
        this.dtInitial = LocalDateTime.now();
        this.company = company;
    }
}
