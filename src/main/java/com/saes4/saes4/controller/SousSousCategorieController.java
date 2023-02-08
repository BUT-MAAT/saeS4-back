package com.saes4.saes4.controller;

import com.saes4.saes4.model.SousSousCategorie;
import com.saes4.saes4.service.SousSousCategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sous_sous_categorie")
public class SousSousCategorieController {
    @Autowired
    private SousSousCategorieService sousSousCategorieService;

    @GetMapping("/all")
    public List<SousSousCategorie> getAllSousCategorie() {
        return sousSousCategorieService.getAllSousSousCategorie();
    }

    @GetMapping("/by_parent")
    public List<SousSousCategorie> getSousCategorieByParent(@RequestParam(value = "id_parent") final Long id_parent) {
        return sousSousCategorieService.getSousCategorieByParent(id_parent);
    }
}
