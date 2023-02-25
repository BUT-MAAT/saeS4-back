package com.saes4.saes4.integration.mock;

import com.saes4.saes4.model.entities.Aliment;
import com.saes4.saes4.model.entities.Sondage;
import com.saes4.saes4.repository.AlimentRepository;
import com.saes4.saes4.repository.SondageRepository;
import com.saes4.saes4.service.triggers.SondageTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class SondageMock {
    @Autowired
    private SondageRepository sondageRepository;

    @Autowired
    private AlimentRepository alimentRepository;

    @Autowired
    private AlimentMock alimentMock;

    @Autowired
    private SondageTrigger sondageTrigger;

    public Sondage getSondage() {
        Sondage sondage = new Sondage();

        // On définit toutes les valeurs du sondage
        sondage.setNom("nom");
        sondage.setPrenom("prenom");
        sondage.setMail("nom.prenom@domaine.com");
        sondage.setVille("ville");
        sondage.setCode_postal("00000");
        sondage.setDate_reponse(new Date());

        // On sélectionne 10 aliments à donner à l'utilisateur
        List<Aliment> aliments = new ArrayList<>();
        List<Aliment> referentiels = alimentRepository.findAll();
        aliments.addAll(referentiels.subList(0, 9));
        sondage.setListe_aliments(aliments);

        return sondage;
    }

    public Sondage createAndGetSondage() {
        return this.sondageRepository.saveAndFlush( getSondage() );
    }

    /*
    * La categorie la plus choisie sera la 1
    *                           puis la 2
    *                           puis la 3
    * L'aliment le plus choisi sera le 25628
    * */
    public List<Sondage> createAndGet3SondagesStatistiques() {
        List<Sondage> sondages = new ArrayList<>();
        for (Sondage sondage : get3SondagesStatistiques()) {
            sondages.add(sondageRepository.saveAndFlush(sondage));
            sondageTrigger.triggerStatistiquesGenerales();
        }
        return sondages;
    }

    private List<Sondage> get3SondagesStatistiques() {
        // 3 selections normalement
        final Long ALIMENTPLUSCHOISI = (long) 25628;
        // 16 selections
        final Long CATEGORIE1 = (long) 10100;
        // 10 selections
        final Long CATEGORIE2 = (long) 20101;
        // 4 selections
        final Long CATEGORIE3 = (long) 30101;

        List<Sondage> sondages = new ArrayList<>();

        // Premier sondage
        Sondage sondage1 = new Sondage();
        sondage1.setNom("nom1");
        sondage1.setPrenom("prenom1");
        sondage1.setMail("nom1.prenom1@domaine.com");
        sondage1.setVille("ville");
        sondage1.setCode_postal("00000");
        sondage1.setDate_reponse(new Date());
        List<Aliment> aliments1 = new ArrayList<>();
        aliments1.addAll(alimentMock.getAlimentsBySousSousCategorie(CATEGORIE1, 9));
        aliments1.add(alimentRepository.findById(ALIMENTPLUSCHOISI).get());
        sondage1.setListe_aliments(aliments1);
        
        sondages.add(sondage1);

        // Deuxieme sondage
        Sondage sondage2 = new Sondage();
        sondage2.setNom("nom2");
        sondage2.setPrenom("prenom2");
        sondage2.setMail("nom2.prenom2@domaine.com");
        sondage2.setVille("ville");
        sondage2.setCode_postal("00000");
        sondage2.setDate_reponse(new Date());
        List<Aliment> aliments2 = new ArrayList<>();
        aliments2.addAll(alimentMock.getAlimentsBySousSousCategorie(CATEGORIE1, 4));
        aliments2.addAll(alimentMock.getAlimentsBySousSousCategorie(CATEGORIE2, 5));
        aliments2.add(alimentRepository.findById(ALIMENTPLUSCHOISI).get());
        sondage2.setListe_aliments(aliments2);

        sondages.add(sondage2);

        // Troisieme sondage
        Sondage sondage3 = new Sondage();
        sondage3.setNom("nom3");
        sondage3.setPrenom("prenom3");
        sondage3.setMail("nom3.prenom3@domaine.com");
        sondage3.setVille("ville");
        sondage3.setCode_postal("00000");
        sondage3.setDate_reponse(new Date());
        List<Aliment> aliments3 = new ArrayList<>();
        aliments3.addAll(alimentMock.getAlimentsBySousSousCategorie(CATEGORIE2, 5));
        aliments3.addAll(alimentMock.getAlimentsBySousSousCategorie(CATEGORIE3, 4));
        aliments3.add(alimentRepository.findById(ALIMENTPLUSCHOISI).get());
        sondage3.setListe_aliments(aliments3);

        sondages.add(sondage3);

        return sondages;
    }
}
