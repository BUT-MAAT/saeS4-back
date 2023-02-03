package com.saes4.saes4.service;

import com.saes4.saes4.model.Categorie;
import com.saes4.saes4.repository.CategorieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CategorieService {
    @Autowired
    CategorieRepository categorieRepository;

    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }
}
