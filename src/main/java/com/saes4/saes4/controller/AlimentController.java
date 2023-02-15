package com.saes4.saes4.controller;

import com.saes4.saes4.mapper.AlimentMapper;
import com.saes4.saes4.model.dto.AlimentDTO;
import com.saes4.saes4.model.entities.Aliment;
import com.saes4.saes4.service.AlimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/aliment")
public class AlimentController {
    @Autowired
    AlimentService alimentService;

    @Autowired
    AlimentMapper alimentMapper;

    @GetMapping("/all")
    public List<AlimentDTO> getAllAliment() { return alimentService.getAllAliments(); }

    @GetMapping("/by_soussouscategorie")
    public List<AlimentDTO> getAlimentsBySousSousCategorie(@RequestParam(value = "soussouscategorie_id") final Long soussouscategorie_id,
                                                        @RequestParam(value = "valeurs_nutritives") final boolean valeurs_nutritives) {
        return alimentService.getAlimentsBySousSousCategorie(soussouscategorie_id, valeurs_nutritives);
    }
}
