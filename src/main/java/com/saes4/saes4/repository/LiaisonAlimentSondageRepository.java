package com.saes4.saes4.repository;

import com.saes4.saes4.model.entities.liaisons.LiaisonAlimentSondage;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional()
public interface LiaisonAlimentSondageRepository extends JpaRepository<LiaisonAlimentSondage, Long>,
        JpaSpecificationExecutor<LiaisonAlimentSondage> {

    @Query(value = "SELECT TOP(1) id_aliment " +
            "FROM CHOIX_ALIMENTS_SONDAGE " +
            "GROUP BY id_aliment " +
            "ORDER BY COUNT(id_aliment) DESC", nativeQuery = true)
    Long getMostSelectedAlimentId();

    @Query(value = "SELECT TOP(1) COUNT(id_aliment) " +
            "FROM CHOIX_ALIMENTS_SONDAGE " +
            "GROUP BY id_aliment " +
            "ORDER BY COUNT(id_aliment) DESC", nativeQuery = true)
    Long getMostSelectedAlimentCount();

    @Query(value = "SELECT TOP(1) id_categorie " +
            "FROM V_CategoriesTrieesParSelection", nativeQuery = true)
    Long getMostSelectedCategorieId();

    @Query(value = "SELECT TOP(1) NB_SELECTIONS " +
            "FROM V_CategoriesTrieesParSelection", nativeQuery = true)
    Long getMostSelectedCategorieCount();
}
