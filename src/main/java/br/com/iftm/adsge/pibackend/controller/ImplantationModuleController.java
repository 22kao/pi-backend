package br.com.iftm.adsge.pibackend.controller;

import br.com.iftm.adsge.pibackend.model.ImplantationModule;
import br.com.iftm.adsge.pibackend.model.dto.ImplantationModuleDto;
import br.com.iftm.adsge.pibackend.service.ImplantationModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("module/implantation")
@RequiredArgsConstructor
public class ImplantationModuleController {

    private final ImplantationModuleService service;

    @GetMapping
    public ResponseEntity<List<ImplantationModuleDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }


}
