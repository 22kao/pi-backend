package br.com.iftm.adsge.pibackend.model.dto;

import br.com.iftm.adsge.pibackend.model.Address;
import br.com.iftm.adsge.pibackend.model.Company;
import br.com.iftm.adsge.pibackend.model.Phone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CompanyDetailed {

    private Integer id;
    private String name;
    private String document;
    private String email;
    private List<Phone> phones = new ArrayList<>();
    private Address address;

    public CompanyDetailed(Integer id, String name, String document, String email) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.email = email;
    }

    public CompanyDetailed(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.document = company.getDocument();
        this.email = company.getEmail();
    }

    public Company toEntity() {
        return Company.builder().id(null).name(name).document(document).email(email).build();
    }
}
