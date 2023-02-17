package com.saes4.saes4.mock;

import com.saes4.saes4.model.entities.Categorie;
import com.saes4.saes4.model.enums.TYPE_CATEGORIE;
import com.saes4.saes4.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

public class CategorieMock {

    private static long NB_MOCKS = 0;

    @Autowired
    private CategorieRepository categorieRepository;

    private Categorie createCategorie(TYPE_CATEGORIE type, Categorie parent) {
        NB_MOCKS++;
        Categorie categorie = new Categorie();

        categorie.setId_categorie(NB_MOCKS);
        categorie.setNom_categorie(type.toString() + NB_MOCKS);
        categorie.setType_categorie(type);
        categorie.setCategorie_parent(parent);

        return categorie;
    }

    public Categorie createAndGetCategorie(TYPE_CATEGORIE type, Categorie parent) {
        Categorie categorie = createCategorie(type, parent);
        return categorieRepository.saveAndFlush(categorie);
    }
}
