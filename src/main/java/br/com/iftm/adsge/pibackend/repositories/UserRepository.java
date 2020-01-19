package br.com.iftm.adsge.pibackend.repositories;

import br.com.iftm.adsge.pibackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

}
