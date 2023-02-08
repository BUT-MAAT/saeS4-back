package com.saes4.saes4.repository;

import com.saes4.saes4.model.SousSousCategorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SousSousCategorieRepository extends JpaRepository<SousSousCategorie, Long>,
                                            JpaSpecificationExecutor<SousSousCategorie> {
}
