package br.com.iftm.adsge.pibackend.model.dto;

import br.com.iftm.adsge.pibackend.model.Address;
import br.com.iftm.adsge.pibackend.model.Company;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {

    private String cep;
    private String city;
    private String street;
    private Integer number;
    @JsonIgnore
    private Company company;

    public AddressDTO(String cep, String city, String street, Integer number) {
        this.cep = cep;
        this.city = city;
        this.street = street;
        this.number = number;
    }

    public AddressDTO(Address address) {
        this.cep = address.getCep();
        this.city = address.getCity();
        this.street = address.getStreet();
        this.number = address.getNumber();
    }

    public Address toEntity() {
        Address address = Address.builder()
                .city(city)
                .street(street)
                .number(number).build();
        if(company != null)
            address.setCompany(company);

        return address;
    }
}
