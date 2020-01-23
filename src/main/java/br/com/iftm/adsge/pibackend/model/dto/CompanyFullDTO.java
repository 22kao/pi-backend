package br.com.iftm.adsge.pibackend.model.dto;

import br.com.iftm.adsge.pibackend.model.Address;
import br.com.iftm.adsge.pibackend.model.Company;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.swing.text.html.Option;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    private AddressDTO address;
    private List<PhoneDTO> phones = new ArrayList<>();

    public CompanyFullDTO(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.document = company.getDocument();
        this.email = company.getEmail();
        updateAddressAndPhones(company);
    }

    public Company toEntity() {
        Company company = Company.builder()
                .id(id)
                .name(name)
                .document(document)
                .email(email).build();
        setAddressAndPhones(company);
        return company;
    }

    private void updateAddressAndPhones(Company company){
        if(company.getAddress().isPresent())
            this.address = new AddressDTO(company.getAddress().get());

        this.phones = company.getPhones()
                .stream()
                .map(e -> new PhoneDTO(e))
                .collect(Collectors.toList());
    }


    private void setAddressAndPhones(Company company) {
        if(!phones.isEmpty())
            company.setPhones(phones.stream().map(e -> e.toEntity()).collect(Collectors.toList()));
        if(address != null){
            address.setCompany(company);
            company.setAddress(address.toEntity());
        }
    }
}