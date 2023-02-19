package com.saes4.saes4.controller;

import com.saes4.saes4.mapper.AlimentMapper;
import com.saes4.saes4.model.dto.AlimentDTO;
import com.saes4.saes4.service.AlimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/by_soussouscategorie/{id}")
    public List<AlimentDTO> getAlimentsBySousSousCategorie(@PathVariable(value = "id") final Long soussouscategorie_id,
                                                           @RequestParam(value = "valeurs_nutritives") final boolean valeurs_nutritives) {
        return alimentService.getAlimentsBySousSousCategorie(soussouscategorie_id, valeurs_nutritives);

    }
}
