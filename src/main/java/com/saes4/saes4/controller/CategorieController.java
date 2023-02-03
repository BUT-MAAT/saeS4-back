package com.saes4.saes4.controller;

import com.saes4.saes4.model.Categorie;
import com.saes4.saes4.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categorie")
public class CategorieController {

    @Autowired
    CategorieService categorieService;

    @GetMapping("/all")
    public List<Categorie> getAllCategorie() {
        return categorieService.getAllCategories();
    }
}
