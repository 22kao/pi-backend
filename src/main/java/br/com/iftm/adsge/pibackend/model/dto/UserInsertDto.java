package br.com.iftm.adsge.pibackend.model.dto;

import br.com.iftm.adsge.pibackend.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class UserInsertDto {

    private Integer id;
    private String name;
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email must be valid")
    private String email;
    private String phone;
    @NotBlank(message = "Password cannot be blank")
    private String password;

    public User toEntity(){
        return User.builder()
                .id(null)
                .name(name)
                .email(email)
                .phone(phone)
                .password(password).build();
    }
}