package br.com.iftm.adsge.pibackend.controller;

import br.com.iftm.adsge.pibackend.model.Phone;
import br.com.iftm.adsge.pibackend.model.dto.CompanyDetailed;
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
    public ResponseEntity<List<CompanyDetailed>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CompanyDetailed> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<CompanyDetailed> insert(@RequestBody CompanyDetailed dto) {
        CompanyDetailed company = service.save(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(company.getId()).toUri();
        return ResponseEntity.created(location).body(company);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CompanyDetailed> update(@PathVariable Integer id, @RequestBody CompanyDetailed dto){
        return ResponseEntity.ok().body(service.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    //Phone's Company
    @PostMapping(value = "/{id}/phones")
    public ResponseEntity<List<Phone>> setPhoneList(@PathVariable Integer companyId, @RequestBody List<Phone> phones){
        List<Phone> newPhones = service.setPhoneList(companyId, phones);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(companyId).toUri();
        return ResponseEntity.created(location).body(newPhones);
    }
}
