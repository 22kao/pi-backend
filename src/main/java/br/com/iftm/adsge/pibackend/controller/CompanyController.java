package br.com.iftm.adsge.pibackend.controller;

import br.com.iftm.adsge.pibackend.model.dto.CompanyDto;
import br.com.iftm.adsge.pibackend.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService service;

    @GetMapping
    public ResponseEntity<List<CompanyDto>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CompanyDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<CompanyDto> insert(@RequestBody CompanyDto dto) {
        CompanyDto company = service.save(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(company.getId()).toUri();
        return ResponseEntity.created(location).body(company);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CompanyDto> update(@PathVariable Integer id, @RequestBody CompanyDto dto){
        return ResponseEntity.ok().body(service.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
