package br.com.iftm.adsge.pibackend.model.dto;
import br.com.iftm.adsge.pibackend.model.ImplantationModule;
import br.com.iftm.adsge.pibackend.model.compositekey.ModuleImplantationId;
import br.com.iftm.adsge.pibackend.model.enums.ProgressStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ImplantationModuleDto {
    private Integer userId;
    private Long implantationId;
    private ProgressStatus status;
    private LocalDateTime dtInitial;
    private LocalDateTime dtEnd;
    private Integer score;
    private String userResponsible;

    public ImplantationModuleDto(ImplantationModule moduleImp) {
        this.userId = moduleImp.getId().getUserId();
        this.status = moduleImp.getStatus();
        this.dtInitial = moduleImp.getDtInitial();
        this.dtEnd = moduleImp.getDtEnd();
        this.score = moduleImp.getScore();
        this.userResponsible = moduleImp.getUserResponsible();
    }

    public ImplantationModule toEntity(){
        return ImplantationModule.builder()
                .id(null)
                .status(status)
                .score(score)
                .dtInitial(dtInitial)
                .dtEnd(dtEnd)
                .userResponsible(userResponsible)
                .build();
    }
}
