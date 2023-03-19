package com.saes4.saes4.model.dto;

import com.saes4.saes4.model.entities.ValeursNutritives;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class AlimentDTO {
    private Long id_aliment;

    private String nom_aliment;

    private Long id_sous_sous_categorie;

    private ValeursNutritivesDTO valeurs_nutritives;
}
