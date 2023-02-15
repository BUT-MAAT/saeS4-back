package com.saes4.saes4.mapper;

import com.saes4.saes4.model.dto.AlimentDTO;
import com.saes4.saes4.model.entities.Aliment;
import com.saes4.saes4.model.entities.ValeursNutritives;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlimentMapper {
    @Named("noValeursNutritives")
    @Mapping(target = "valeurs_nutritives", ignore = true)
    AlimentDTO noValeursNutritives(Aliment aliment);

    @Named("withValeursNutritives")
    @Mapping(target = "valeurs_nutritives", ignore = false)
    AlimentDTO withValeursNutritives(Aliment aliment);

    @Named("noValeursNutritivesList")
    @IterableMapping(qualifiedByName="noValeursNutritives")
    List<AlimentDTO> noValeursNutritivesList(List<Aliment> aliments);

    @Named("withValeursNutritivesList")
    @IterableMapping(qualifiedByName="withValeursNutritives")
    List<AlimentDTO> withValeursNutritivesList(List<Aliment> aliments);
}
