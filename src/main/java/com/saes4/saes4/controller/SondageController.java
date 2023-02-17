package com.saes4.saes4.controller;

import com.saes4.saes4.model.dto.SondageDTO;
import com.saes4.saes4.model.entities.Sondage;
import com.saes4.saes4.service.SondageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sondage")
public class SondageController {

    @Autowired
    private SondageService sondageService;

    @PostMapping("/create")
    public SondageDTO createSondage(@RequestBody SondageDTO reponse) {
        return sondageService.createSondage(reponse);
    }
}
