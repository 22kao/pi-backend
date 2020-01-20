package br.com.iftm.adsge.pibackend.model.pk;

import br.com.iftm.adsge.pibackend.model.ModuleImplantation;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class ModuleImplantationPk implements Serializable {

    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "implantation_id")
    private Integer implantationId;

    public ModuleImplantationPk(){}

    public ModuleImplantationPk(Integer userId, Integer implantationId) {
        this.userId = userId;
        this.implantationId = implantationId;
    }
}
