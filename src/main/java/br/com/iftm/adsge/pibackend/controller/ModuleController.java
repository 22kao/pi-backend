package br.com.iftm.adsge.pibackend.controller;

import br.com.iftm.adsge.pibackend.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/module")
public class ModuleController {

    private final ModuleService service;
}
