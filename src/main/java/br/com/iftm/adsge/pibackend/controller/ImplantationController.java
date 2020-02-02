package br.com.iftm.adsge.pibackend.controller;

import br.com.iftm.adsge.pibackend.model.dto.ImplantationDto;
import br.com.iftm.adsge.pibackend.model.dto.ImplantationModuleDto;
import br.com.iftm.adsge.pibackend.service.ImplantationService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
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
    public ResponseEntity<List<ImplantationDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ImplantationDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping(value = "/company/{id}")
    public ResponseEntity<List<ImplantationDto>> findAllByCompanyId(
            @PathVariable Integer id) {
        return ResponseEntity.ok(service.findAllByCompanyId(id));
    }

    @PostMapping(value = "/company/{id}")
    public ResponseEntity<ImplantationDto> save(@PathVariable Integer id,
                                                @Valid @RequestBody ImplantationDto implantationDto) {
        ImplantationDto newImplantationDto = service.save(id, implantationDto);

        URI location = ServletUriComponentsBuilder
                .fromPath("/implantation/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).body(newImplantationDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ImplantationDto> update(@PathVariable Long id, @Valid @RequestBody ImplantationDto implantationDto) {
        return ResponseEntity.ok(service.update(id, implantationDto));
    }

    /*
     * Implantation Modules
     */

    @GetMapping(value = "/{id}/modules")
    public ResponseEntity<List<ImplantationModuleDto>> findAllModulesByImplantationId(@PathVariable Long id) {
        return ResponseEntity.ok(service.FindAllModules(id));
    }

    @PostMapping(value = "/{id}/modules")
    public ResponseEntity<Void> addImplantationModule(@PathVariable Long id,
                                                                       @Valid @RequestBody ImplantationModuleDto impModuleDto) {
        service.addImplantationModule(id, impModuleDto);
        return ResponseEntity.noContent().build();
    }
}
