package com.saes4.saes4.mapper;

import com.saes4.saes4.model.dto.AlimentDTO;
import com.saes4.saes4.model.dto.ValeursNutritivesDTO;
import com.saes4.saes4.model.entities.Aliment;
import com.saes4.saes4.model.entities.ValeursNutritives;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ValeurNutritivesMapper {

    @Named("valeursNutritivesDTOToValeursNutritives")
    ValeursNutritives valeursNutritivesDTOToValeursNutritives(ValeursNutritivesDTO valeursNutritives);

    @Named("valeursNutritivesToValeursNutritivesDTO")
    ValeursNutritivesDTO valeursNutritivesToValeursNutritivesDTO(ValeursNutritives valeursNutritives);
}
