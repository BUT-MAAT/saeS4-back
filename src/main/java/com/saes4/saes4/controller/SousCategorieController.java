package com.saes4.saes4.controller;

import com.saes4.saes4.model.SousCategorie;
import com.saes4.saes4.service.SousCategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sous_categorie")
public class SousCategorieController {
    @Autowired
    private SousCategorieService sousCategorieService;

    @GetMapping("/all")
    public List<SousCategorie> getAllSousCategorie() {
        return sousCategorieService.getAllSousCategorie();
    }

    @GetMapping("/by_parent")
    public List<SousCategorie> getSousCategorieByParent(@RequestParam(value = "id_parent") final Long id_parent) {
        return sousCategorieService.getSousCategorieByParent(id_parent);
    }
}
