package com.saes4.saes4.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.saes4.saes4.model.entities.Categorie;
import com.saes4.saes4.model.enums.TYPE_CATEGORIE;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategorieDTO {

    private Long id_categorie;

    private String nom_categorie;

    private TYPE_CATEGORIE type_categorie;

    private CategorieDTO categorie_parent;
}
