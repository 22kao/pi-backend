package br.com.iftm.adsge.pibackend.controller;

import br.com.iftm.adsge.pibackend.model.Implantation;
import br.com.iftm.adsge.pibackend.model.dto.RequestImplantation;
import br.com.iftm.adsge.pibackend.model.dto.ResponseImplantation;
import br.com.iftm.adsge.pibackend.service.ImplantationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/implantation")
@RequiredArgsConstructor
public class ImplantationController {

    private final ImplantationService service;

    @GetMapping
    public ResponseEntity<List<ResponseImplantation>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseImplantation> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping(value = "/company/{id}")
    public ResponseEntity<List<ResponseImplantation>> findAllByCompanyId(
            @PathVariable Integer id) {
        return ResponseEntity.ok(service.findAllByCompanyId(id));
    }

    @PostMapping(value = "/company/{id}")
    public ResponseEntity<ResponseImplantation> save(@PathVariable Integer id,
            @Valid @RequestBody RequestImplantation requestImp){
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).body(service.save(id, requestImp));
    }
}
