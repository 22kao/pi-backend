package br.com.iftm.adsge.pibackend.model.dto;

import br.com.iftm.adsge.pibackend.model.Implantation;
import br.com.iftm.adsge.pibackend.model.enums.ProgressStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ImplantationDto {

    private Long id;

    @NotBlank(message = "Implantation's description cannot be blank")
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dtExpectedInitial;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dtExpected;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @PastOrPresent(message = "Implantation realized date cannot be in the future")
    private LocalDateTime dtRealized;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @PastOrPresent(message = "Implantation initial date cannot be in the future")
    private LocalDateTime dtInitial;

    private ProgressStatus status;

    public ImplantationDto(Implantation imp) {
        this.id = imp.getId();
        this.description = imp.getDescription();
        this.dtExpectedInitial = imp.getDtExpectedInitial();
        this.dtExpected = imp.getDtExpected();
        this.dtRealized = imp.getDtRealized();
        this.dtInitial = imp.getDtInitial();
        this.status = imp.getStatus();
    }

    public Implantation toEntity() {
        return Implantation.builder()
                .id(null)
                .description(description)
                .dtExpected(dtExpected)
                .dtExpectedInitial(dtExpectedInitial)
                .dtRealized(dtRealized)
                .dtInitial(dtInitial)
                .status(status)
                .build();
    }
}