package br.com.iftm.adsge.pibackend.repository;

import br.com.iftm.adsge.pibackend.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CompanyRepository extends JpaRepository<Company, Integer> {
}