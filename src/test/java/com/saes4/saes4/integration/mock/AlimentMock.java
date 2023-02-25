package com.saes4.saes4.integration.mock;

import com.saes4.saes4.model.entities.Aliment;
import com.saes4.saes4.repository.AlimentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlimentMock {

    @Autowired
    private AlimentRepository alimentRepository;

    public List<Aliment> getAlimentsBySousSousCategorie(Long id) {
        List<Aliment> aliments = alimentRepository.findAll()
                .stream()
                .filter(aliment -> aliment.getId_sous_sous_categorie().equals(id))
                .toList();
        return aliments;
    }

    public List<Aliment> getAlimentsBySousSousCategorie(Long id, Integer nombreAliments) {
        return getAlimentsBySousSousCategorie(id).subList(0, nombreAliments);
    }
}
