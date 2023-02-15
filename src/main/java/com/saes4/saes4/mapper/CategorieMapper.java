package com.saes4.saes4.mapper;

import com.saes4.saes4.model.dto.AlimentDTO;
import com.saes4.saes4.model.dto.CategorieDTO;
import com.saes4.saes4.model.entities.Aliment;
import com.saes4.saes4.model.entities.Categorie;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategorieMapper {
    @Named("categorieMapper")
    @Mapping(target = "categorie_parent", ignore = true)
    CategorieDTO categorieMapper(Categorie categorie);

    @Named("categorieMapperLisy")
    @IterableMapping(qualifiedByName="categorieMapper")
    List<CategorieDTO> categorieMapperList(List<Categorie> categories);
}
