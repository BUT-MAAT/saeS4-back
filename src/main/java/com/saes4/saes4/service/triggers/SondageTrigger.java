package com.saes4.saes4.service.triggers;

import com.saes4.saes4.model.entities.Aliment;
import com.saes4.saes4.model.entities.Categorie;
import com.saes4.saes4.model.entities.StatistiquesGenerales;
import com.saes4.saes4.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SondageTrigger {
    @Autowired
    private SondageRepository sondageRepository;
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
        Long nbSelectionsAliment = liaisonAlimentSondageRepository.getMostSelectedAlimentCount();
        Categorie categorieLaPlusChoisie = categorieRepository.findById(
                liaisonAlimentSondageRepository.getMostSelectedCategorieId()
        ).get();
        Long nbSelectionsCategorie = liaisonAlimentSondageRepository.getMostSelectedCategorieCount();

        statistiquesGenerales.setTimelog(new Date());
        statistiquesGenerales.setNombre_reponses(nbReponse);
        statistiquesGenerales.setAliment_plus_choisi(alimentLePlusChoisi);
        statistiquesGenerales.setNb_selections_aliment(nbSelectionsAliment);
        statistiquesGenerales.setCategorie_plus_choisi(categorieLaPlusChoisie);
        statistiquesGenerales.setNb_selections_categorie(nbSelectionsCategorie);
        statistiquesGeneralesRepository.save(statistiquesGenerales);
    }
}
