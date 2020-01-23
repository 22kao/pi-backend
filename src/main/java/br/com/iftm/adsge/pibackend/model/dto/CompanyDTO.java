package br.com.iftm.adsge.pibackend.model.dto;

import br.com.iftm.adsge.pibackend.model.Address;
import br.com.iftm.adsge.pibackend.model.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {

    private Integer id;
    private String name;
    private String document;

    public CompanyDTO(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.document = company.getDocument();
    }

    public Company toEntity() {
        Company company = Company.builder()
                .id(id)
                .name(name)
                .document(document).build();

        return company;
    }
}
