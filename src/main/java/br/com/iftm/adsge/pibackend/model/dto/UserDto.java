package br.com.iftm.adsge.pibackend.model.dto;

import br.com.iftm.adsge.pibackend.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private Integer id;
    private String name;
    private String email;
    private String phone;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
    }
}