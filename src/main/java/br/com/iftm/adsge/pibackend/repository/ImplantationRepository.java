package br.com.iftm.adsge.pibackend.repository;

import br.com.iftm.adsge.pibackend.model.Implantation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ImplantationRepository extends JpaRepository<Implantation, Long> {

    //@Query("SELECT imp FROM Implantation imp WHERE imp.company.document = :document")
    List<Implantation> findAllByCompanyDocument(String document);
}
