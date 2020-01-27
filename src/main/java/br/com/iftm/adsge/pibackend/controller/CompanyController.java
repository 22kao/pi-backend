package br.com.iftm.adsge.pibackend.controller;

import br.com.iftm.adsge.pibackend.model.Phone;
import br.com.iftm.adsge.pibackend.model.dto.CompanyBasic;
import br.com.iftm.adsge.pibackend.model.dto.PhoneCompany;
import br.com.iftm.adsge.pibackend.service.CompanyService;
import br.com.iftm.adsge.pibackend.service.PhoneService;
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
    private final PhoneService phoneService;

    @GetMapping
    public ResponseEntity<List<CompanyBasic>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CompanyBasic> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<CompanyBasic> insert(@RequestBody CompanyBasic dto) {
        CompanyBasic newCompany = service.save(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCompany.getId()).toUri();
        return ResponseEntity.created(location).body(newCompany);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CompanyBasic> update(@PathVariable Integer id, @RequestBody CompanyBasic dto){
        return ResponseEntity.ok().body(service.update(id, dto));
    }

    //Phone's Company
    @GetMapping(value = "/{id}/phones")
    public ResponseEntity<List<Phone>> findAllPhonesByCompanyId(@PathVariable Integer id){
        return ResponseEntity.ok(phoneService.findAllByCompanyId(id));
    }

    @PostMapping(value = "/{id}/phones")
    public ResponseEntity<List<Phone>> addPhoneList(@PathVariable Integer id, @RequestBody List<Phone> phones){
        List<Phone> newPhones = service.addPhoneList(id, phones);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(id).toUri();
        return ResponseEntity.created(location).body(newPhones);
    }

    //todo address controller para adição de de endereço no cnpj informado
    //todo phones controller para adição de telefone no cpnj informado
}
