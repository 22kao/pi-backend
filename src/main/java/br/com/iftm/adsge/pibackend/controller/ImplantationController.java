package br.com.iftm.adsge.pibackend.controller;

import br.com.iftm.adsge.pibackend.model.dto.ImplantationDTO;
import br.com.iftm.adsge.pibackend.service.ImplantationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/implantation")
@RequiredArgsConstructor
public class ImplantationController {

    private final ImplantationService service;

    @GetMapping(value = "/company/{document}")
    public ResponseEntity<List<ImplantationDTO>> findAllByCompanyDocument(@PathVariable String document){
        return ResponseEntity.ok().body(service.findAllByCompanyDocument(document));
    }
}
