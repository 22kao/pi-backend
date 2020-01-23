package br.com.iftm.adsge.pibackend.model.dto;

import br.com.iftm.adsge.pibackend.model.Address;
import br.com.iftm.adsge.pibackend.model.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {

    private Integer id;
    private String name;
    private String document;
    private String email;
    private Address address;
    private List<PhoneDTO> phones = new ArrayList<>();

    public CompanyDTO(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.document = company.getDocument();
        this.email = company.getEmail();
    }

    public Company toEntity() {
        Company company = Company.builder()
                .id(id)
                .name(name)
                .document(document)
                .email(email).build();

        return company;
    }
}
