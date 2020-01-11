package br.com.iftm.adsge.pibackend.repository;

import br.com.iftm.adsge.pibackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserJpaRepository extends JpaRepository<User, Long> {

}
