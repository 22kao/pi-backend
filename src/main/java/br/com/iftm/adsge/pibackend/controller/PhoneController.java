package br.com.iftm.adsge.pibackend.controller;

import br.com.iftm.adsge.pibackend.service.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/phones")
@RequiredArgsConstructor
public class PhoneController {

    private final PhoneService service;
}
