package com.saes4.saes4.mapper;

import com.saes4.saes4.model.dto.StatistiquesDTO;
import com.saes4.saes4.model.entities.Statistiques;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {AlimentMapper.class, CategorieMapper.class})
public interface StatistiquesMapper {
    // Pour un mapping complet, nécessite le complément
    @Named("statistiquesGeneralesToStatistiquesGeneralesDTO")
    @Mapping(target = "aliment_plus_choisi", qualifiedByName = "alimentToAlimentStatistiquesDTO")
    @Mapping(target = "categorie_plus_choisi", qualifiedByName = "categorieToCategorieStatistiquesDTO")
    @Mapping(target = "aliments_top_10", ignore = true)
    @Mapping(target = "categories_triees", ignore = true)
    StatistiquesDTO statistiquesGeneralesToStatistiquesGeneralesDTO(Statistiques statistiques);

    // Précision du mapping de base
    default StatistiquesDTO complementStatistiquesGeneralesToStatistiquesGeneralesDTO(Statistiques statistiques, StatistiquesDTO dto) {
        dto.getAliment_plus_choisi().setNb_selections(
                statistiques.getNb_selections_aliment()
        );
        dto.getAliment_plus_choisi().setRank(1);

        dto.getCategorie_plus_choisi().setNb_selections(
                statistiques.getNb_selections_categorie()
        );
        dto.getCategorie_plus_choisi().setRank(1);

        return dto;
    }
}
