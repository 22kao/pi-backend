package br.com.iftm.adsge.pibackend.controller;

import br.com.iftm.adsge.pibackend.model.dto.PhoneCompany;
import br.com.iftm.adsge.pibackend.service.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<PhoneCompany> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(service.findById(id));
    }
}
