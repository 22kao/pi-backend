package br.com.iftm.adsge.pibackend.controller;

import br.com.iftm.adsge.pibackend.model.ImplantationModule;
import br.com.iftm.adsge.pibackend.model.dto.ImplantationModuleDto;
import br.com.iftm.adsge.pibackend.model.dto.ObservationDto;
import br.com.iftm.adsge.pibackend.service.ImplantationModuleService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("implantation/modules")
@RequiredArgsConstructor
public class ImplantationModuleController {

    private final ImplantationModuleService service;

    @GetMapping
    public ResponseEntity<List<ImplantationModuleDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImplantationModuleDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImplantationModuleDto> update(@PathVariable Long id,
                                                        @Valid @RequestBody ImplantationModuleDto impModuleDto) {
        return ResponseEntity.ok(service.update(id, impModuleDto));
    }

    // Observation
    @GetMapping("/{id}/observation")
    public ResponseEntity<List<ObservationDto>> findAllObservations(@PathVariable Long id){
        return ResponseEntity.ok(service.findAllObservations(id));
    }

    @PostMapping("/{id}/observation")
    public ResponseEntity<Void> addObservation(@PathVariable Long id, @Valid @RequestBody ObservationDto obsDto){
        service.addObservation(id, obsDto);
        return ResponseEntity.noContent().build();
    }
}
