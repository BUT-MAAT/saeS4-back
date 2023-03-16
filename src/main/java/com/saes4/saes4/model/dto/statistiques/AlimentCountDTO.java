package com.saes4.saes4.model.dto.statistiques;

import com.saes4.saes4.model.dto.AlimentDTO;
import com.saes4.saes4.model.dto.ValeursNutritivesDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AlimentCountDTO implements Comparable{

    private Long id_aliment;

    private String nom_aliment;

    private Long id_sous_sous_categorie;

    private ValeursNutritivesDTO valeurs_nutritives;

    private Long nbChoisi;
    @Override
    public int compareTo(Object o) {
        AlimentCountDTO compareAlimentCountDTO = (AlimentCountDTO) o;
        return (int) (compareAlimentCountDTO.nbChoisi -  this.nbChoisi);
    }
}
