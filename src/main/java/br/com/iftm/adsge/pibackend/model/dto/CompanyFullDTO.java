package br.com.iftm.adsge.pibackend.model.dto;

import br.com.iftm.adsge.pibackend.model.Address;
import br.com.iftm.adsge.pibackend.model.Company;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyFullDTO {

    private Integer id;
    private String name;
    private String document;
    private String email;
    private Address address;
    private List<PhoneDTO> phones = new ArrayList<>();

    public CompanyFullDTO(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.document = company.getDocument();
        this.email = company.getEmail();

        if(company.getAddress() != null)
            this.address = company.getAddress();

        this.phones = company.getPhones().stream().map(e -> new PhoneDTO(e)).collect(Collectors.toList());
    }

    public Company toEntity() {
        Company company = Company.builder()
                .id(id)
                .name(name)
                .document(document)
                .email(email).build();

        if(!phones.isEmpty())
            company.setPhones(phones.stream().map(e -> e.toEntity()).collect(Collectors.toList()));

        //if(address != null)
          //  company.setAddress(address);

        return company;
    }
}