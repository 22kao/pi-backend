package br.com.iftm.adsge.pibackend.model.dto;

import br.com.iftm.adsge.pibackend.model.Observation;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ObservationDto {

    private Long id;

    @NotBlank(message = "Observation description cannot be null")
    private String description;

    private String username;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedOn;

    public ObservationDto(Observation observation){
        if(observation.getUser() == null)
            throw new IllegalArgumentException("Cannot instantiate ObservationDto: User was null.");

        this.id = observation.getId();
        this.description = observation.getDescription();
        this.username = observation.getUser().getName();
        this.createdOn = observation.getCreatedOn();
        this.updatedOn = observation.getUpdatedOn();
    }
}
