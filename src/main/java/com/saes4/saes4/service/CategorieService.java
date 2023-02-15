package com.saes4.saes4.service;

import com.saes4.saes4.mapper.CategorieMapper;
import com.saes4.saes4.model.dto.CategorieDTO;
import com.saes4.saes4.model.entities.Categorie;
import com.saes4.saes4.model.enums.TYPE_CATEGORIE;
import com.saes4.saes4.repository.CategorieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CategorieService {
    @Autowired
    CategorieRepository categorieRepository;

    @Autowired
    CategorieMapper categorieMapper;

    public List<CategorieDTO> getAllCategories() {
        List<Categorie> categories = categorieRepository.findAll()
                .stream()
                .filter((categorie -> categorie.getType_categorie() == TYPE_CATEGORIE.CATEGORIE))
                .toList();
        return categorieMapper.categorieMapperList(categories);
    }

    public List<CategorieDTO> getCategoriesByParentId(Long parent_id) {
        List<Categorie> categories = categorieRepository.findAll()
                .stream()
                .filter((categorie -> categorie.getType_categorie() != TYPE_CATEGORIE.CATEGORIE
                        && categorie.getCategorie_parent().getId_categorie().equals(parent_id)))
                .toList();
        return categorieMapper.categorieMapperList(categories);
    }
}
