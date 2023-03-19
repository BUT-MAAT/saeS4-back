package com.saes4.saes4.mapper;

import com.saes4.saes4.model.dto.AlimentDTO;
import com.saes4.saes4.model.dto.SondageDTO;
import com.saes4.saes4.model.entities.Aliment;
import com.saes4.saes4.model.entities.Sondage;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AlimentMapper.class})
public interface SondageMapper {

    @Named("sondageDTOToSondage")
    @Mapping(target = "liste_aliments", qualifiedByName = "alimentDTOToAlimentWithValeursNutritivesList")
    Sondage sondageDTOToSondage(SondageDTO sondage);

    @Named("sondageToSondageDTO")
    @Mapping(target = "liste_aliments", qualifiedByName = "alimentToAlimentDTOWithValeursNutritivesList")
    SondageDTO sondageToSondageDTO(Sondage sondage);


}
