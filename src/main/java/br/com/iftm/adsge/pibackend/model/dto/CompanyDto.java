package br.com.iftm.adsge.pibackend.model.dto;

import br.com.iftm.adsge.pibackend.model.Address;
import br.com.iftm.adsge.pibackend.model.Company;
import br.com.iftm.adsge.pibackend.model.Phone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    private Integer id;
    @NotBlank(message = "Company name cannot be blank")
    private String name;
    @NotBlank(message = "Company document cannot be blank")
    private String document;
    @Email(message = "Email should be valid")
    private String email;
    private List<PhoneDto> phones = new ArrayList<>();
    private AddressDto address;

    public CompanyDto(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.document = company.getDocument();
        this.email = company.getEmail();
    }

    public Company toEntity() {
        return Company.builder()
                .id(null)
                .name(name)
                .document(document)
                .email(email).build();
    }
}
