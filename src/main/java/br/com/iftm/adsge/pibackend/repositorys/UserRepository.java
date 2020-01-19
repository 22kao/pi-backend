package br.com.iftm.adsge.pibackend.repositorys;

import br.com.iftm.adsge.pibackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends JpaRepository<User, Long> {

}
