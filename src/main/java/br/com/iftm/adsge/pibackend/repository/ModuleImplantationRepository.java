package br.com.iftm.adsge.pibackend.repository;

import br.com.iftm.adsge.pibackend.model.ModuleImplantation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ModuleImplantationRepository extends JpaRepository<ModuleImplantation, Long> {
}
