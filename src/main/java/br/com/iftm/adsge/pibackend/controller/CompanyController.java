package br.com.iftm.adsge.pibackend.controller;

import br.com.iftm.adsge.pibackend.model.Address;
import br.com.iftm.adsge.pibackend.model.dto.AddressDTO;
import br.com.iftm.adsge.pibackend.model.dto.CompanyDTO;
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
    public ResponseEntity<List<CompanyDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CompanyDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<CompanyDTO> insert(@RequestBody CompanyDTO dto) {
        CompanyDTO newCompany = service.save(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCompany.getId()).toUri();
        return ResponseEntity.created(location).body(newCompany);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CompanyDTO> update(@PathVariable Integer id, @RequestBody CompanyDTO dto){
        return ResponseEntity.ok().body(service.update(id, dto));
    }


    //todo address controller para adição de de endereço no cnpj informado
    //todo phones controller para adição de telefone no cpnj informado
}
