package br.com.iftm.adsge.pibackend.model;

import br.com.iftm.adsge.pibackend.model.enums.ProgressStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.Set;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Implantation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private LocalDateTime dtExpectedInitial;
    private LocalDateTime dtExpected;
    @PastOrPresent(message = "Implantation realized date cannot be in the future")
    private LocalDateTime dtRealized;
    @PastOrPresent(message = "Implantation initial date cannot be in the future")
    private LocalDateTime dtInitial;
    @Enumerated(value = EnumType.STRING)
    private ProgressStatus status;

    @OneToMany(mappedBy = "implantation")
    private Set<ModuleImplantation> modulesImplantation;

    @OneToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    //todo relação module_implantation
}
