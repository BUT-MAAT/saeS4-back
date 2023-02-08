package com.saes4.saes4.repository;

import com.saes4.saes4.model.SousCategorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SousCategorieRepository extends JpaRepository<SousCategorie, Long>,
                                            JpaSpecificationExecutor<SousCategorie> {
}
