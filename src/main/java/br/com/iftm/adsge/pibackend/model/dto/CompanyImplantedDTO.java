package br.com.iftm.adsge.pibackend.model.dto;

import br.com.iftm.adsge.pibackend.model.Company;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyImplantedDTO {

    private String name;
    private String document;

    public CompanyImplantedDTO(Company company) {
        this.name = company.getName();
        this.document = company.getDocument();
    }

    public Company toEntity() {
        return Company.builder().document(document).name(name).build();
    }
}
