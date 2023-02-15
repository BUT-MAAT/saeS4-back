package com.saes4.saes4.mapper;

import com.saes4.saes4.model.dto.AlimentDTO;
import com.saes4.saes4.model.entities.Aliment;
import com.saes4.saes4.model.entities.ValeursNutritives;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ValeurNutritivesMapper.class})
public interface AlimentMapper {
    @Named("alimentToAlimentDTONoValeursNutritives")
    @Mapping(target = "valeurs_nutritives", ignore = true)
    AlimentDTO alimentToAlimentDTONoValeursNutritives(Aliment aliment);

    @Named("alimentToAlimentDTOWithValeursNutritives")
    @Mapping(target = "valeurs_nutritives", qualifiedByName = "valeursNutritivesToValeursNutritivesDTO")
    AlimentDTO alimentToAlimentDTOWithValeursNutritives(Aliment aliment);

    @Named("alimentDTOToAlimentWithValeursNutritives")
    @Mapping(target = "valeurs_nutritives", qualifiedByName = "valeursNutritivesDTOToValeursNutritives")
    Aliment alimentDTOToAlimentWithValeursNutritives(AlimentDTO aliment);

    @Named("alimentToAlimentDTONoValeursNutritivesList")
    @IterableMapping(qualifiedByName="alimentToAlimentDTONoValeursNutritives")
    List<AlimentDTO> alimentToAlimentDTONoValeursNutritivesList(List<Aliment> aliments);

    @Named("alimentToAlimentDTOWithValeursNutritivesList")
    @IterableMapping(qualifiedByName="alimentToAlimentDTOWithValeursNutritives")
    List<AlimentDTO> alimentToAlimentDTOWithValeursNutritivesList(List<Aliment> aliments);

    @Named("alimentDTOToAlimentWithValeursNutritivesList")
    @IterableMapping(qualifiedByName="alimentDTOToAlimentWithValeursNutritives")
    List<Aliment> alimentDTOToAlimentWithValeursNutritivesList(List<AlimentDTO> aliments);
}
