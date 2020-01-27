package br.com.iftm.adsge.pibackend.repository;

import br.com.iftm.adsge.pibackend.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

    List<Phone> findAllByCompanyId(Integer companyId);
}
