package com.saes4.saes4.controller;

import com.saes4.saes4.model.dto.StatistiquesDTO;
import com.saes4.saes4.model.dto.statistiques.AlimentCountDTO;
import com.saes4.saes4.service.AlimentService;
import com.saes4.saes4.service.StatistiquesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/statistiques")
public class StatistiquesController {

    @Autowired
    private StatistiquesService statistiquesService;


    @GetMapping("/")
    public StatistiquesDTO getStatistiques() {
        return statistiquesService.getLastStatistiques();
    }

    @GetMapping("MostConsumedByDepartment/{id}")
    public List<AlimentCountDTO> getMostConsumedAlimentsByDepartment(@PathVariable(value = "id") final String department, @RequestParam(value = "size", required = false) final Long size) {
        List<AlimentCountDTO> alimentCountDTO = statistiquesService.getMostConsumedAlimentsByDepartment(department,size);
        return alimentCountDTO;
    }

}
