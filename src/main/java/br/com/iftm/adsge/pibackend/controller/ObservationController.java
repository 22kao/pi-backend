package br.com.iftm.adsge.pibackend.controller;

import br.com.iftm.adsge.pibackend.model.dto.ObservationDto;
import br.com.iftm.adsge.pibackend.service.ObservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("observation")
public class ObservationController {

    private final ObservationService service;

    @GetMapping
    public ResponseEntity<List<ObservationDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObservationDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObservationDto> update(@PathVariable Long id, @Valid @RequestBody ObservationDto obsDto){
        return ResponseEntity.ok(service.update(id, obsDto));
    }
}
