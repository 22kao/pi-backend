package br.com.iftm.adsge.pibackend.model.dto;

import br.com.iftm.adsge.pibackend.model.Company;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CompanyDTO {

    private Integer id;
    private String name;
    private String document;
    private String email;
    private AddressDTO address;
    private List<PhoneDTO> phones;

    public CompanyDTO(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.document = company.getDocument();
        this.email = company.getEmail();
        this.phones = company.getPhones()
                .stream()
                .map(e -> new PhoneDTO(e))
                .collect(Collectors.toList());

        if (company.getAddress() != null)
            this.address = new AddressDTO(company.getAddress());
    }

    public Company toEntity() {
        return Company.builder()
                .id(id)
                .document(document)
                .email(email)
                .address(address.toEntity())
                .phones(phones.stream()
                        .map(e -> e.toEntity())
                        .collect(Collectors.toList())).build();
    }
}