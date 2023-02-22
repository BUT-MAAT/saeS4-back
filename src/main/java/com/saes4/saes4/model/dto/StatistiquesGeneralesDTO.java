package com.saes4.saes4.model.dto;

import jakarta.persistence.TableGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class StatistiquesGeneralesDTO {
    private Long id_statistiques;

    private Date timelog;

    private Long nombre_reponses;

    private AlimentDTO aliment_plus_choisi;

    private CategorieDTO categorie_plus_choisi;
}
