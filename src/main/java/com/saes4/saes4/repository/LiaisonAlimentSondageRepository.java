package com.saes4.saes4.repository;

import com.saes4.saes4.model.entities.liaisons.LiaisonAlimentSondage;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional()
public interface LiaisonAlimentSondageRepository extends JpaRepository<LiaisonAlimentSondage, Long>,
        JpaSpecificationExecutor<LiaisonAlimentSondage> {

    @Query(value = "SELECT id_aliment " +
            "FROM CHOIX_ALIMENTS_SONDAGE " +
            "GROUP BY id_aliment " +
            "ORDER BY COUNT(id_aliment) DESC " +
            "LIMIT 1", nativeQuery = true)
    Long getMostSelectedAlimentId();

    @Query(value = "SELECT COUNT(id_aliment) " +
            "FROM CHOIX_ALIMENTS_SONDAGE " +
            "GROUP BY id_aliment " +
            "ORDER BY COUNT(id_aliment) DESC " +
            "LIMIT 1", nativeQuery = true)
    Long getMostSelectedAlimentCount();

    @Query(value = "SELECT id_categorie " +
            "FROM V_CategoriesTrieesParSelection " +
            "LIMIT 1", nativeQuery = true)
    Long getMostSelectedCategorieId();

    @Query(value = "SELECT NB_SELECTIONS " +
            "FROM V_CategoriesTrieesParSelection " +
            "LIMIT 1", nativeQuery = true)
    Long getMostSelectedCategorieCount();

    @Query(value = "SELECT id_aliment " +
            "FROM CHOIX_ALIMENTS_SONDAGE " +
            "GROUP BY id_aliment " +
            "ORDER BY COUNT(id_aliment) DESC " +
            "LIMIT 10", nativeQuery = true)
    List<Long> get10MostSelectedAlimentsId();

    @Query(value = "SELECT COUNT(id_aliment) " +
            "FROM CHOIX_ALIMENTS_SONDAGE " +
            "GROUP BY id_aliment " +
            "ORDER BY COUNT(id_aliment) DESC " +
            "LIMIT 10", nativeQuery = true)
    List<Long> get10MostSelectedAlimentsCount();

    @Query(value = "SELECT id_categorie " +
            "FROM V_CategoriesTrieesParSelection ", nativeQuery = true)
    List<Long> getSortedCategoriesId();

    @Query(value = "SELECT NB_SELECTIONS " +
            "FROM V_CategoriesTrieesParSelection ", nativeQuery = true)
    List<Long> getSortedCategorieCount();
}
