package br.com.iftm.adsge.pibackend.repository;

import br.com.iftm.adsge.pibackend.model.ImplantationModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImplantationModuleRepository extends JpaRepository<ImplantationModule, Long> {
    List<ImplantationModule> findAllByImplantationId(Long id);
}
