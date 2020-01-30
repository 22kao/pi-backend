package br.com.iftm.adsge.pibackend.model.dto;
import br.com.iftm.adsge.pibackend.model.ModuleImplantation;
import br.com.iftm.adsge.pibackend.model.compositekey.ModuleImplantationId;
import br.com.iftm.adsge.pibackend.model.enums.ProgressStatus;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BasicModuleImplantation {
    private Integer userId;
    private Long implantationId;
    private ProgressStatus status;
    /*@JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)*/
    private LocalDateTime dtInitial;
    private LocalDateTime dtEnd;
    private Integer score;
    private String userResponsible;

    public BasicModuleImplantation(ModuleImplantation moduleImp) {
        this.userId = moduleImp.getId().getUserId();
        this.implantationId = moduleImp.getId().getImplantationId();
        this.status = moduleImp.getStatus();
        this.dtInitial = moduleImp.getDtInitial();
        this.dtEnd = moduleImp.getDtEnd();
        this.score = moduleImp.getScore();
        this.userResponsible = moduleImp.getUserResponsible();
    }

    public ModuleImplantation toEntity(){
        return ModuleImplantation.builder()
                .id(new ModuleImplantationId(userId, implantationId))
                .status(status)
                .score(score)
                .dtInitial(dtInitial)
                .dtEnd(dtEnd)
                .userResponsible(userResponsible)
                .build();
    }
}
