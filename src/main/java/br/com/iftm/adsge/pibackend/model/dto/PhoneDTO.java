package br.com.iftm.adsge.pibackend.model.dto;

import br.com.iftm.adsge.pibackend.model.Company;
import br.com.iftm.adsge.pibackend.model.Phone;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneDTO {

    private String phone;
    private String username;
    @JsonIgnore
    private Company company;

    public PhoneDTO(Phone phone) {
        this.phone = phone.getPhone();
        this.username = phone.getUsername();
    }

    public Phone toEntity() {
        return new Phone(null, phone, username, company);
    }
}
