package br.com.iftm.adsge.pibackend.model.dto;

import br.com.iftm.adsge.pibackend.model.Phone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PhoneCompany {

    private Long id;
    private String phone;
    private String username;
    private Integer companyId;
    private String companyName;

    public PhoneCompany(Phone phone) {
        if(phone.getCompany() == null)
            throw new IllegalArgumentException("Error instantiating PhoneDTO: Company was null");

        this.id = phone.getId();
        this.phone = phone.getPhone();
        this.username = phone.getUsername();
        this.companyId = phone.getCompany().getId();
        this.companyName = phone.getCompany().getName();
    }

    public Phone toEntity(){
        return new Phone(null, phone, username, null);
    }
}
