package br.com.iftm.adsge.pibackend.repository;

import br.com.iftm.adsge.pibackend.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
