package br.com.iftm.adsge.pibackend.services;

import br.com.iftm.adsge.pibackend.models.User;
import br.com.iftm.adsge.pibackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }
}