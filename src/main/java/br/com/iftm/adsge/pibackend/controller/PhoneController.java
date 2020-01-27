package br.com.iftm.adsge.pibackend.controller;

import br.com.iftm.adsge.pibackend.model.dto.PhoneCompany;
import br.com.iftm.adsge.pibackend.service.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/phones")
@RequiredArgsConstructor
public class PhoneController {

    private final PhoneService service;

    @GetMapping
    public ResponseEntity<List<PhoneCompany>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PhoneCompany> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PhoneCompany> update(@PathVariable Long id, @RequestBody PhoneCompany phone){
        return ResponseEntity.ok(service.update(id, phone));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
