package br.com.iftm.adsge.pibackend.model.dto;

import br.com.iftm.adsge.pibackend.model.Module;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class ModuleDto {

    private Integer id;

    @NotBlank(message = "Module description cannot be blank")
    private String description;

    public ModuleDto(Module module) {
        this.id = module.getId();
        this.description = module.getDescription();
    }
}
