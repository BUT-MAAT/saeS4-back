package com.saes4.saes4.model.dto;

import com.saes4.saes4.model.entities.ValeursNutritives;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AlimentDTO {
    private Long id_aliment;

    private String nom_aliment;

    private Long id_sous_sous_categorie;

    private ValeursNutritives valeurs_nutritives;
}
