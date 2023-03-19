package com.saes4.saes4.service;

import com.saes4.saes4.mapper.AlimentMapper;
import com.saes4.saes4.mapper.SondageMapper;
import com.saes4.saes4.model.dto.AlimentDTO;
import com.saes4.saes4.model.dto.statistiques.AlimentCountDTO;
import com.saes4.saes4.model.entities.Aliment;
import com.saes4.saes4.model.entities.Sondage;
import com.saes4.saes4.model.entities.ValeursNutritives;
import com.saes4.saes4.repository.AlimentRepository;
import com.saes4.saes4.repository.SondageRepository;
import com.saes4.saes4.repository.ValeursNutritivesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
@Transactional
public class AlimentService {
    @Autowired
    AlimentRepository alimentRepository;

    @Autowired
    SondageMapper sondageMapper;

    @Autowired
    AlimentMapper alimentMapper;

    @Autowired
    ValeursNutritivesRepository valeursNutritivesRepository;

    @Autowired
    SondageRepository sondageRepository;

    public List<AlimentDTO> getAllAliments() {
        List<Aliment> aliments = alimentRepository.findAll();
        return alimentMapper.alimentToAlimentDTONoValeursNutritivesList(aliments);
    }


    public List<AlimentDTO> getAlimentsBySousSousCategorie(final Long soussouscategorie_id, final boolean valeurs_nutritives) {
        List<Aliment> aliments = alimentRepository.findAll()
                .stream()
                .filter(aliment -> aliment.getId_sous_sous_categorie().equals(soussouscategorie_id))
                .toList();
        if (valeurs_nutritives) return alimentMapper.alimentToAlimentDTOWithValeursNutritivesList(aliments);
        return alimentMapper.alimentToAlimentDTONoValeursNutritivesList(aliments);
    }


}
