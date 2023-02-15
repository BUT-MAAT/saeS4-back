package com.saes4.saes4.controller;

import com.saes4.saes4.model.entities.Categorie;
import com.saes4.saes4.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategorieController {

    @Autowired
    CategorieService categorieService;

    @GetMapping("/categorie/all")
    public List<Categorie> getAllCategories() {
        return categorieService.getAllCategories();
    }

    @GetMapping("/by_parent/{id}")
    public List<Categorie> getCategoriesByParentId(@PathVariable(value = "id") final Long parent_id) {
        return categorieService.getCategoriesByParentId(parent_id);
    }
}
