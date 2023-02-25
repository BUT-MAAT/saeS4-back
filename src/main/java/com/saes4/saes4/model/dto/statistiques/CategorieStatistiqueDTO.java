package com.saes4.saes4.model.dto.statistiques;

import com.saes4.saes4.model.dto.CategorieDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategorieStatistiqueDTO {

    private Long  id_categorie;

    private String nom_categorie;

    private Long nb_selections;

    private CategorieDTO categorie_parent;
}
