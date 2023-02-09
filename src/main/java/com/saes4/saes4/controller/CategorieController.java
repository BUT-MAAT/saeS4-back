package com.saes4.saes4.controller;

import com.saes4.saes4.model.Categorie;
import com.saes4.saes4.model.enums.TYPE_CATEGORIE;
import com.saes4.saes4.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/by_parent")
    public List<Categorie> getCategoriesByParentId(@RequestParam(value = "parent_id") final Long parent_id) {
        return categorieService.getCategoriesByParentId(parent_id);
    }
}
