package com.saes4.saes4.controller;

import com.saes4.saes4.model.Categorie;
import com.saes4.saes4.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CategorieController {

    @Autowired
    CategorieService categorieService;

    public List<Categorie> getAllCategorie() {
        return categorieService.getAllCategories();
    }
}
