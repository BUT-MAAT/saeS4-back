package com.saes4.saes4.service.triggers;

import com.saes4.saes4.model.entities.Aliment;
import com.saes4.saes4.model.entities.Categorie;
import com.saes4.saes4.model.entities.Statistiques;
import com.saes4.saes4.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SondageTrigger {
    @Autowired
    private SondageRepository sondageRepository;
    @Autowired
    private StatistiquesRepository statistiquesRepository;
    @Autowired
    private AlimentRepository alimentRepository;
    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private LiaisonAlimentSondageRepository liaisonAlimentSondageRepository;

    public void triggerStatistiquesGenerales() {
        Statistiques statistiques = new Statistiques();

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

        statistiques.setTimelog(new Date());
        statistiques.setNombre_reponses(nbReponse);
        statistiques.setAliment_plus_choisi(alimentLePlusChoisi);
        statistiques.setNb_selections_aliment(nbSelectionsAliment);
        statistiques.setCategorie_plus_choisi(categorieLaPlusChoisie);
        statistiques.setNb_selections_categorie(nbSelectionsCategorie);
        statistiquesRepository.save(statistiques);
    }
}
