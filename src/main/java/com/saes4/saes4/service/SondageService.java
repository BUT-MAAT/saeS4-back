package com.saes4.saes4.service;

import com.saes4.saes4.mapper.SondageMapper;
import com.saes4.saes4.model.dto.StatistiquesGeneralesDTO;
import com.saes4.saes4.model.entities.Aliment;
import com.saes4.saes4.model.entities.Categorie;
import com.saes4.saes4.model.entities.Sondage;
import com.saes4.saes4.model.dto.SondageDTO;
import com.saes4.saes4.model.entities.StatistiquesGenerales;
import com.saes4.saes4.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Transactional
public class SondageService {

    @Autowired
    private SondageRepository sondageRepository;

    @Autowired
    private SondageMapper sondageMapper;

    public SondageDTO createSondage(SondageDTO reponse) {
        Sondage reponseEntity = sondageMapper.sondageDTOToSondage(reponse);
        sondageRepository.save(reponseEntity);
        triggerStatistiquesGenerales();
        return reponse;
    }

    // Le code suivant remlace un trigger par implementable sur H2

    @Autowired
    private StatistiquesGeneralesRepository statistiquesGeneralesRepository;

    @Autowired
    private AlimentRepository alimentRepository;

    @Autowired
    private CategorieRepository categorieRepository;

    @Autowired
    private LiaisonAlimentSondageRepository liaisonAlimentSondageRepository;

    public void triggerStatistiquesGenerales() {
        StatistiquesGenerales statistiquesGenerales = new StatistiquesGenerales();

        Long nbReponse = sondageRepository.findAll()
                .stream()
                .count();
        Aliment alimentLePlusChoisi = alimentRepository.findById(
                liaisonAlimentSondageRepository.getMostSelectedAlimentId()
        ).get();
        Categorie categorieLaPlusChoisie = categorieRepository.findById(
                liaisonAlimentSondageRepository.getMostSelectedCategorieId()
        ).get();

        statistiquesGenerales.setTimelog(new Date());
        statistiquesGenerales.setNombre_reponses(nbReponse);
        statistiquesGenerales.setAliment_plus_choisi(alimentLePlusChoisi);
        statistiquesGenerales.setCategorie_plus_choisi(categorieLaPlusChoisie);

        statistiquesGeneralesRepository.save(statistiquesGenerales);
    }
}
