package br.com.iftm.adsge.pibackend.repository;

import br.com.iftm.adsge.pibackend.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ModuleRepository extends JpaRepository<Module, Integer> {
}
