package br.com.iftm.adsge.pibackend.model.compositekey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleImplantationId implements Serializable {

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "implantation_id")
    private Integer implantationId;
}
