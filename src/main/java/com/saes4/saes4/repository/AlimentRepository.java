package com.saes4.saes4.repository;

import com.saes4.saes4.model.entities.Aliment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AlimentRepository extends JpaRepository<Aliment, Long>,
                                        JpaSpecificationExecutor<Aliment> {
}
