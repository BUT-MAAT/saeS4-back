package com.saes4.saes4.integration.mock;

import com.saes4.saes4.model.entities.Aliment;
import com.saes4.saes4.model.entities.Sondage;
import com.saes4.saes4.repository.AlimentRepository;
import com.saes4.saes4.repository.SondageRepository;
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

    public Sondage getSondage() {
        Sondage sondage = new Sondage();

        // On définit toutes les valeurs du sondage
        sondage.setNom("nom");
        sondage.setPrenom("prenom");
        sondage.setMail("nom.prenom@domaine.com");
        sondage.setVille("ville");
        sondage.setCode_postal("00000");
        sondage.setDate_reponse(new Date());

        // On selectionne 10 aliments a donner à l'utilisateur
        ArrayList<Aliment> aliments = new ArrayList<>();
        List<Aliment> referentiels = alimentRepository.findAll();
        for (int i = 0; i < 10; i++) {
            aliments.add(referentiels.get(i));
        }
        sondage.setListe_aliments(aliments);

        return sondage;
    }

    public Sondage createAndGetSondage() {
        return this.sondageRepository.saveAndFlush( getSondage() );
    }
}
