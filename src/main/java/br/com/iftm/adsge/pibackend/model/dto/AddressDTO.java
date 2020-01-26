package br.com.iftm.adsge.pibackend.model.dto;

import br.com.iftm.adsge.pibackend.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddressDTO {

    private Integer id;
    private String cep;
    private String street;
    private String city;
    private String state;
    private Integer number;
    private Integer companyId;
    private String companyName;

    public AddressDTO(Address address) {
        if(address.getCompany() == null)
            throw  new IllegalArgumentException("Error instantiating AddressDTO: Company was null");

        this.id = address.getId();
        this.cep = address.getCep();
        this.street = address.getStreet();
        this.city = address.getCity();
        this.state = address.getState();
        this.number = address.getNumber();
        this.companyId = address.getCompany().getId();
        this.companyName = address.getCompany().getName();
    }

    public Address toEntity(){
        return Address.builder().id(null).cep(cep).street(street).state(state).city(city).number(number).build();
    }
}
