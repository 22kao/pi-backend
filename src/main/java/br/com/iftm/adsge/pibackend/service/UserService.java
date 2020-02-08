package br.com.iftm.adsge.pibackend.service;

import br.com.iftm.adsge.pibackend.model.User;
import br.com.iftm.adsge.pibackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email);
        if(user == null)
            throw new UsernameNotFoundException(String.format("User with email %s", email));
        return user;
    }
}
