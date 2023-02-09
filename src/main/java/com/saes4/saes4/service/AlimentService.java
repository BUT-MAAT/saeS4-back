package com.saes4.saes4.service;

import com.saes4.saes4.model.Aliment;
import com.saes4.saes4.model.ValeursNutritives;
import com.saes4.saes4.repository.AlimentRepository;
import com.saes4.saes4.repository.ValeursNutritivesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AlimentService {
    @Autowired
    AlimentRepository alimentRepository;

    @Autowired
    ValeursNutritivesRepository valeursNutritivesRepository;

    public List<Aliment> getAllAliments() {
        return alimentRepository.findAll();
    }


    public List<Aliment> getAlimentsBySousSousCategorie(Long soussouscategorie_id) {
        return alimentRepository.findAll()
                .stream()
                .filter(aliment -> aliment.getId_sous_sous_categorie().equals(soussouscategorie_id))
                .toList();
    }

    public Optional<ValeursNutritives> getValeursNutritivesAliment(Long aliment_id) {
        return valeursNutritivesRepository.findById(aliment_id);
    }
}
