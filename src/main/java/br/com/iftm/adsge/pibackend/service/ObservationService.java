package br.com.iftm.adsge.pibackend.service;

import br.com.iftm.adsge.pibackend.model.Observation;
import br.com.iftm.adsge.pibackend.model.User;
import br.com.iftm.adsge.pibackend.model.dto.ObservationDto;
import br.com.iftm.adsge.pibackend.repository.ObservationRepository;
import br.com.iftm.adsge.pibackend.repository.UserRepository;
import br.com.iftm.adsge.pibackend.service.exceptions.DatabaseException;
import br.com.iftm.adsge.pibackend.service.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ObservationService {

    private final ObservationRepository repository;
    private final UserRepository userRepository;

    public List<ObservationDto> findAll() {
        List<Observation> list = repository.findAll();
        return list.stream().map(e -> new ObservationDto(e)).collect(Collectors.toList());
    }

    public ObservationDto findById(Long id) {
        Optional<Observation> obj = repository.findById(id);
        Observation observation = obj.orElseThrow(
                ()-> new ResourceNotFoundException(String.format("Observation id %s not found", id)));
        return new ObservationDto(observation);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(String.format("Observation id %s not found", id));
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public ObservationDto update(Long id, ObservationDto obsDto) {
        try{
            Observation observation = repository.getOne(id);
            updateData(observation, obsDto);
            return new ObservationDto(repository.save(observation));

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(String.format("Implantation id %s not found", id));
        }
    }

    private void updateData(Observation observation, ObservationDto obsDto) {
        User user = userRepository.findByName(obsDto.getUsername());

        //todo altera para usuário autenticado
        if(user == null)
            throw new ResourceNotFoundException(String.format("Username '%s' not found", obsDto.getUsername()));
        observation.setUser(user);
        observation.setDescription(obsDto.getDescription());
    }
}
