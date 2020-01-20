package br.com.iftm.adsge.pibackend.repository;

import br.com.iftm.adsge.pibackend.model.Observation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ObservationRepository extends JpaRepository<Observation, Long> {
}
