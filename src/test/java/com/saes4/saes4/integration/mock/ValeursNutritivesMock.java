package com.saes4.saes4.integration.mock;

import com.saes4.saes4.model.entities.ValeursNutritives;
import com.saes4.saes4.repository.ValeursNutritivesRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValeursNutritivesMock {

    @Autowired
    private ValeursNutritivesRepository valeursNutritivesRepository;

    private ValeursNutritives createValeursNutritives(Long id) {
        ValeursNutritives valeursNutritives = new ValeursNutritives();

        valeursNutritives.setId_valeurs_nutritives(id);

        return valeursNutritives;
    }

    public ValeursNutritives createAndGetValeursNutritives(Long id) {
        ValeursNutritives valeursNutritives = createValeursNutritives(id);
        return valeursNutritivesRepository.saveAndFlush(valeursNutritives);
    }
}
