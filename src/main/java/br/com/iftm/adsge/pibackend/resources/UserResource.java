package br.com.iftm.adsge.pibackend.resources;

import br.com.iftm.adsge.pibackend.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @GetMapping
    public ResponseEntity<User> findAll(){
        User u = new User(1L,"Maria", "maria@gmail.com", "3224-7481", "12345" );
        return ResponseEntity.ok().body(u);
    }

}
