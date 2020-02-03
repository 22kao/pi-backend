package br.com.iftm.adsge.pibackend.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Observation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Include
    @EqualsAndHashCode.Include
    private String description;

    @ManyToOne
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "implantation_module_id")
    private ImplantationModule implantationModule;

    @PastOrPresent(message = "Observation creation date cannot be on future")
    private LocalDateTime createdOn;

    @PastOrPresent(message = "Observation update date cannot be on future")
    private LocalDateTime updatedOn;

    @PrePersist
    public void prePersist(){
        createdOn = LocalDateTime.now();
        //todo set authenticated user
    }

    @PreUpdate
    public void preUpdate(){
        updatedOn = LocalDateTime.now();
    }
}