package br.com.iftm.adsge.pibackend.repository;

import br.com.iftm.adsge.pibackend.model.Implantation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ImplantationRepository extends JpaRepository<Implantation, Integer> {
}
