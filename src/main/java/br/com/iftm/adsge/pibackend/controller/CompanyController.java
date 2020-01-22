package br.com.iftm.adsge.pibackend.controller;

import br.com.iftm.adsge.pibackend.model.dto.CompanyDTO;
import br.com.iftm.adsge.pibackend.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService service;

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    //todo address controller para adição de de endereço no cnpj informado
    //todo phones controller para adição de telefone no cpnj informado
}
