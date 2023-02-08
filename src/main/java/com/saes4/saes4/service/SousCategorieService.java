package com.saes4.saes4.service;

import com.saes4.saes4.model.SousCategorie;
import com.saes4.saes4.repository.SousCategorieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SousCategorieService {
    @Autowired
    private SousCategorieRepository sousCategorieRepository;

    public List<SousCategorie> getAllSousCategorie() { return sousCategorieRepository.findAll(); }

    public List<SousCategorie> getSousCategorieByParent(final Long id_parent) {
        return sousCategorieRepository.findAll()
                .stream()
                .filter((sousCategorie) -> sousCategorie.getCategorie_parent().getId_categorie().equals(id_parent))
                .toList();
    }
}
