package com.saes4.saes4.model.dto;

import com.saes4.saes4.model.dto.statistiques.AlimentStatistiquesDTO;
import com.saes4.saes4.model.dto.statistiques.CategorieStatistiqueDTO;
import jakarta.persistence.TableGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class StatistiquesDTO {
    private Long id_statistiques;

    private Date timelog;

    private Long nombre_reponses;

    private AlimentStatistiquesDTO aliment_plus_choisi;

    private CategorieStatistiqueDTO categorie_plus_choisi;

    private List<AlimentStatistiquesDTO> aliments_top_10;

    private List<CategorieStatistiqueDTO> categories_triees;
}
