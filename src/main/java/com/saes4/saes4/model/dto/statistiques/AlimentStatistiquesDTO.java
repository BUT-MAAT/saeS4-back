package com.saes4.saes4.model.dto.statistiques;

import com.saes4.saes4.model.dto.AlimentDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AlimentStatistiquesDTO {

    private Long  id_aliment;

    private String nom_aliment;

    private Long nb_selections;

    public AlimentStatistiquesDTO(AlimentDTO aliment) {
        this.nom_aliment = aliment.getNom_aliment();
    }
}
