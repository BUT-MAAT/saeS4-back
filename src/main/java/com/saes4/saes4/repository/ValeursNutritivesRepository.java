package com.saes4.saes4.repository;

import com.saes4.saes4.model.entities.ValeursNutritives;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
@Transactional()
public interface ValeursNutritivesRepository extends JpaRepository<ValeursNutritives, Long>,
                                    JpaSpecificationExecutor<ValeursNutritives> {
}
