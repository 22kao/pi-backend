package br.com.iftm.adsge.pibackend.repository;

import br.com.iftm.adsge.pibackend.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
