package br.com.iftm.adsge.pibackend.model.dto;

import br.com.iftm.adsge.pibackend.model.Company;
import br.com.iftm.adsge.pibackend.model.Implantation;
import br.com.iftm.adsge.pibackend.model.enums.ProgressStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Getter
@Setter
public class RequestImplantation {

    @NotBlank(message = "Implantation's description cannot be blank")
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dtExpectedInitial;

    @FutureOrPresent(message = "Implantation expected date cannot be in the past")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dtExpected;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @PastOrPresent(message = "Implantation realized date cannot be in the future")
    private LocalDateTime dtRealized;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @PastOrPresent(message = "Implantation initial date cannot be in the future")
    private LocalDateTime dtInitial;

    private ProgressStatus status;

    public Implantation toEntity(){

        return Implantation.builder()
                .id(null)
                .description(description)
                .dtExpected(dtExpected)
                .dtExpectedInitial(dtExpectedInitial)
                .dtRealized(dtRealized)
                .status(status)
                .dtInitial(LocalDateTime.now())
                .build();
    }
}
