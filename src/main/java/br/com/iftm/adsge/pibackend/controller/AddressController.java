package br.com.iftm.adsge.pibackend.controller;

import br.com.iftm.adsge.pibackend.model.dto.AddressDto;
import br.com.iftm.adsge.pibackend.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "address")
public class AddressController {

    private final AddressService service;

    @GetMapping
    public ResponseEntity<List<AddressDto>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AddressDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping(value = "/company/{id}")
    public ResponseEntity<AddressDto> save(@PathVariable Integer companyId, @RequestBody AddressDto dto) {
        AddressDto newDto = service.save(companyId, dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newDto.getId())
                .toUri();
        return ResponseEntity.created(location).body(newDto);
    }

    @PutMapping(value = "/company/{id}")
    public ResponseEntity<AddressDto> update(@PathVariable Integer companyId, @RequestBody AddressDto dto) {
        return ResponseEntity.ok().body(service.update(companyId, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
