package br.com.iftm.adsge.pibackend.model.dto;
import br.com.iftm.adsge.pibackend.model.ImplantationModule;
import br.com.iftm.adsge.pibackend.model.enums.ProgressStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ImplantationModuleDto {
    private Long id;
    private Integer userId;
    @NotNull(message = "Module id cannot be null")
    @Positive(message = "Module id must be a positive number")
    private Integer moduleId;
    private ProgressStatus status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dtInitial;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @PastOrPresent(message = "Module Implantation initial date cannot be in the future")
    private LocalDateTime dtEnd;
    private Integer score;
    private String userResponsible;

    public ImplantationModuleDto(ImplantationModule impModule) {
        if(impModule.getUser() == null)
            throw new IllegalArgumentException("Error instantiating ImplantationModuleDto: User was null");
        if(impModule.getModule() == null)
            throw new IllegalArgumentException("Error instantiating ImplantationModuleDto: Module was null");

        this.id = impModule.getId();
        this.userId = impModule.getUser().getId();
        this.moduleId = impModule.getModule().getId();
        this.status = impModule.getStatus();
        this.dtInitial = impModule.getDtInitial();
        this.dtEnd = impModule.getDtEnd();
        this.score = impModule.getScore();
        this.userResponsible = impModule.getUserResponsible();
    }
}
