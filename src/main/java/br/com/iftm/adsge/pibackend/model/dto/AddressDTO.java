package br.com.iftm.adsge.pibackend.model.dto;

import br.com.iftm.adsge.pibackend.model.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {

    private String city;
    private String street;
    private Integer number;
    @JsonIgnore
    private CompanyDTO company;

    public AddressDTO(Address address) {
        this.city = address.getCity();
        this.street = address.getStreet();
        this.number = address.getNumber();
    }

    public Address toEntity() {
        return Address.builder()
                .city(city)
                .street(street)
                .number(number).build();
    }
}
