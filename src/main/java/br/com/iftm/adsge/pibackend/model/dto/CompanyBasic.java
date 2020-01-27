package br.com.iftm.adsge.pibackend.model.dto;

import br.com.iftm.adsge.pibackend.model.Company;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyBasic {
    private Integer id;
    private String name;
    private String document;
    private String email;

    public CompanyBasic(Integer id, String name, String document, String email) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.email = email;
    }

    public CompanyBasic(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.document = company.getDocument();
        this.email = company.getEmail();
    }

    public Company toEntity(){
        return Company.builder().id(null).name(name).document(document).email(email).build();
    }
}
