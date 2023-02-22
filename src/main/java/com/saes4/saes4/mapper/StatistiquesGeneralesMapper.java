package com.saes4.saes4.mapper;

import com.saes4.saes4.model.dto.StatistiquesGeneralesDTO;
import com.saes4.saes4.model.entities.StatistiquesGenerales;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {AlimentMapper.class, CategorieMapper.class})
public interface StatistiquesGeneralesMapper {
    @Named("statistiquesGeneralesTostatistiquesGeneralesDTO")
    @Mapping(target = "aliment_plus_choisi", qualifiedByName = "alimentToAlimentDTONoValeursNutritives")
    @Mapping(target = "categorie_plus_choisi", qualifiedByName = "categorieToCategorieDTO")
    StatistiquesGeneralesDTO statistiquesGeneralesTostatistiquesGeneralesDTO(StatistiquesGenerales statistiquesGenerales);
}
