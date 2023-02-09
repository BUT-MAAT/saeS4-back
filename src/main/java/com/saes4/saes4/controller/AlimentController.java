package com.saes4.saes4.controller;

import com.saes4.saes4.model.Aliment;
import com.saes4.saes4.model.ValeursNutritives;
import com.saes4.saes4.service.AlimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/aliment")
public class AlimentController {
    @Autowired
    AlimentService alimentService;

    @GetMapping("/all")
    public List<Aliment> getAllAliment() { return alimentService.getAllAliments(); }

    @GetMapping("/by_soussouscategorie")
    public List<Aliment> getAlimentsBySousSousCategorie(@RequestParam(value = "soussouscategorie_id") final Long soussouscategorie_id) {
        return alimentService.getAlimentsBySousSousCategorie(soussouscategorie_id);
    }

    @GetMapping("/valeurs_nutritives")
    public Optional<ValeursNutritives> getValeursNutritivesAliment(@RequestParam(value = "aliment_id") final Long aliment_id) {
        return alimentService.getValeursNutritivesAliment(aliment_id);
    }
}
