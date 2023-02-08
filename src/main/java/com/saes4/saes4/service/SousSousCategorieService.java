package com.saes4.saes4.service;

import com.saes4.saes4.model.SousSousCategorie;
import com.saes4.saes4.repository.SousSousCategorieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SousSousCategorieService {
    @Autowired
    private SousSousCategorieRepository sousSousCategorieRepository;

    public List<SousSousCategorie> getAllSousSousCategorie() { return sousSousCategorieRepository.findAll(); }

    public List<SousSousCategorie> getSousCategorieByParent(final Long id_parent) {
        return sousSousCategorieRepository.findAll()
                .stream()
                .filter((sousSousCategorie) -> sousSousCategorie.getCategorie_parent().getId_sous_categorie().equals(id_parent))
                .toList();
    }
}
