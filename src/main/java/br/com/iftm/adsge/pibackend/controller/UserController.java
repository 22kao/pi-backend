package br.com.iftm.adsge.pibackend.controller;

import br.com.iftm.adsge.pibackend.models.User;
import br.com.iftm.adsge.pibackend.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("/users")
public class UserController {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @GetMapping(value = "/all")
    public List<User> findAll(){
        return userJpaRepository.findAll();

    }
}
