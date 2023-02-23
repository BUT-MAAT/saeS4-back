package com.saes4.saes4.mapper;

import com.saes4.saes4.model.dto.StatistiquesGeneralesDTO;
import com.saes4.saes4.model.entities.StatistiquesGenerales;
import lombok.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {AlimentMapper.class, CategorieMapper.class})
public interface StatistiquesGeneralesMapper {
    // Pour un mapping complet, nécessite le complément
    @Named("statistiquesGeneralesToStatistiquesGeneralesDTO")
    @Mapping(target = "aliment_plus_choisi", qualifiedByName = "alimentToAlimentStatistiquesDTO")
    @Mapping(target = "categorie_plus_choisi", qualifiedByName = "categorieToCategorieStatistiquesDTO")
//    @Mapping(target = "nb_selections_aliment", ignore = true)
//    @Mapping(target = "nb_selections_categorie", ignore = true)
    StatistiquesGeneralesDTO statistiquesGeneralesToStatistiquesGeneralesDTO(StatistiquesGenerales statistiquesGenerales);

    // Précision du mapping de base
    default StatistiquesGeneralesDTO complementStatistiquesGeneralesToStatistiquesGeneralesDTO(StatistiquesGenerales statistiquesGenerales, StatistiquesGeneralesDTO dto) {
        dto.getAliment_plus_choisi().setNb_selections(
                statistiquesGenerales.getNb_selections_aliment()
        );

        dto.getCategorie_plus_choisi().setNb_selections(
                statistiquesGenerales.getNb_selections_categorie()
        );

        return dto;
    }
}
