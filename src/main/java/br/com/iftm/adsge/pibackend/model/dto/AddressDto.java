package br.com.iftm.adsge.pibackend.model.dto;

import br.com.iftm.adsge.pibackend.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AddressDto {

    private Integer id;
    private String cep;
    @NotBlank(message = "Address street cannot be blank")
    private String street;
    private String city;
    private String state;
    @NotNull(message = "Address number cannot be null")
    private Integer number;

    public AddressDto(Address address) {
        this.id = address.getId();
        this.cep = address.getCep();
        this.street = address.getStreet();
        this.city = address.getCity();
        this.state = address.getState();
        this.number = address.getNumber();
    }

    public Address toEntity(){
        return Address.builder()
                .id(null)
                .cep(cep)
                .street(street)
                .state(state)
                .city(city)
                .number(number)
                .build();
    }
}
