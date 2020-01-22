package br.com.iftm.adsge.pibackend.model.dto;

import br.com.iftm.adsge.pibackend.model.Company;
import br.com.iftm.adsge.pibackend.model.Implantation;
import br.com.iftm.adsge.pibackend.model.ModuleImplantation;
import br.com.iftm.adsge.pibackend.model.enums.ProgressStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class ImplantationDTO {

    private Long id;
    private String description;
    private LocalDateTime dtExpectedInitial;
    private LocalDateTime dtExpected;
    private LocalDateTime dtRealized;
    private LocalDateTime dtInitial;
    private ProgressStatus status;
    //todo moduleImplantationDTO
    private Set<ModuleImplantation> modulesImplantation;
    @JsonIgnore
    private CompanyImplantedDTO company;

    public ImplantationDTO(Implantation entity){
        if(entity.getCompany() == null)
            throw new IllegalArgumentException("Error instantiating ImplantationDTO: company was null.");

        this.id = entity.getId();
        this.description = entity.getDescription();
        this.dtExpectedInitial = entity.getDtExpectedInitial();
        this.dtExpected = entity.getDtExpected();
        this.status = entity.getStatus();
        this.modulesImplantation = entity.getModulesImplantation();
        this.company = new CompanyImplantedDTO(entity.getCompany());

        if(entity.getDtRealized() != null)
            this.dtRealized = entity.getDtRealized();

        if(entity.getDtExpectedInitial() != null)
            this.dtExpectedInitial = entity.getDtExpectedInitial();
    }

    public Implantation toEntity(){
        return Implantation.builder()
                .id(id)
                .dtExpected(dtExpected)
                .dtExpectedInitial(dtExpectedInitial)
                .dtRealized(dtRealized)
                .dtInitial(dtInitial)
                .status(status)
                .modulesImplantation(modulesImplantation)
                .company(company.toEntity()).build();
    }
}
