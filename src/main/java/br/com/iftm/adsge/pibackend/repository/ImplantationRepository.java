package br.com.iftm.adsge.pibackend.repository;

import br.com.iftm.adsge.pibackend.model.Implantation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImplantationRepository extends JpaRepository<Implantation, Long> {

    List<Implantation> findAllByCompanyId(Integer id);
}
