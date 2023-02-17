package com.saes4.saes4.integration.mock;

import com.saes4.saes4.model.entities.Aliment;
import com.saes4.saes4.model.entities.ValeursNutritives;
import com.saes4.saes4.repository.AlimentRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AlimentMock {

    private static long NB_MOCKS = 0;

    @Autowired
    private AlimentRepository alimentRepository;

    @Autowired
    private ValeursNutritivesMock valeursNutritivesMock;

    private Aliment createAliment() {
        NB_MOCKS++;
        Aliment aliment = new Aliment();

        aliment.setId_aliment(NB_MOCKS);
        aliment.setNom_aliment("Aliment" + NB_MOCKS);
        aliment.setId_sous_sous_categorie(NB_MOCKS);
        ValeursNutritives valeursNutritives = valeursNutritivesMock.createAndGetValeursNutritives(NB_MOCKS);
        aliment.setValeurs_nutritives(valeursNutritives);

        return aliment;
    }

    public Aliment createAndGetAliment() {
        Aliment aliment = createAliment();
        return alimentRepository.saveAndFlush(aliment);
    }
}
