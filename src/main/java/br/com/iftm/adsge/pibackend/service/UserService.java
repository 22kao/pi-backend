package br.com.iftm.adsge.pibackend.service;

import br.com.iftm.adsge.pibackend.model.User;
import br.com.iftm.adsge.pibackend.model.dto.UserDto;
import br.com.iftm.adsge.pibackend.model.dto.UserInsertDto;
import br.com.iftm.adsge.pibackend.repository.UserRepository;
import br.com.iftm.adsge.pibackend.service.exceptions.DatabaseException;
import br.com.iftm.adsge.pibackend.service.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<UserDto> findAll(){
        List<User> list = repository.findAll();
        return list.stream().map(e -> new UserDto(e)).collect(Collectors.toList());
    }

    public UserDto findById(Integer id){
        Optional<User> obj = repository.findById(id);
        User user = obj.orElseThrow(() ->
                new ResourceNotFoundException(String.format("User id %s not found", id)));
        return new UserDto(user);
    }

    @Transactional
    public UserDto save(UserInsertDto dto){

        User user = dto.toEntity();
        user = repository.save(user);
        return new UserDto(user);
    }

    @Transactional
    public UserDto update(Integer id, UserInsertDto dto){
        try{
            User user = repository.getOne(id);
            updateData(user, dto);
            user = repository.save(user);
            return new UserDto(user);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(String.format("User id %s not found", id));
        }
    }

    public void delete(Integer id){
        try{
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(String.format("User id %s not found", id));
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    //todo reset password service

    private void updateData(User user, UserInsertDto dto) {
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
    }
}
