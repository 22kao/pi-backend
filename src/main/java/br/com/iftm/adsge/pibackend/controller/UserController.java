package br.com.iftm.adsge.pibackend.controller;

import br.com.iftm.adsge.pibackend.model.User;
import br.com.iftm.adsge.pibackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/all")
    public List<User> findAll(){
        return userRepository.findAll();
    }
}
