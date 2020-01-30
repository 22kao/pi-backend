package br.com.iftm.adsge.pibackend.model.dto;

import br.com.iftm.adsge.pibackend.model.Implantation;
import br.com.iftm.adsge.pibackend.model.enums.ProgressStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseImplantation {

    private Long id;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dtExpectedInitial;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dtExpected;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dtRealized;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dtInitial;
    private ProgressStatus status;

    public ResponseImplantation(Implantation imp) {
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
                .description(description)
                .dtExpected(dtExpected)
                .dtExpectedInitial(dtExpectedInitial)
                .dtRealized(dtRealized)
                .dtInitial(dtInitial)
                .status(status)
                .build();
    }
}