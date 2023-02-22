package com.saes4.saes4.controller;

import com.saes4.saes4.model.dto.AlimentDTO;
import com.saes4.saes4.model.dto.StatistiquesDTO;
import com.saes4.saes4.service.StatistiquesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistiques")
public class StatistiquesController {

    @Autowired
    private StatistiquesService statistiquesService;

    @GetMapping("/")
    public StatistiquesDTO getStatistiques() {
        return statistiquesService.getLastStatistiques();
    }
}
