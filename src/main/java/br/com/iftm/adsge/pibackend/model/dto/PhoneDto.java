package br.com.iftm.adsge.pibackend.model.dto;

import br.com.iftm.adsge.pibackend.model.Phone;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class PhoneDto {

    private Long id;
    @NotBlank(message = "Phone number cannot be blank")
    private String phone;
    private String username;

    public PhoneDto(Phone phone) {
        this.id = phone.getId();
        this.phone = phone.getPhone();
        this.username = phone.getUsername();
    }
}
