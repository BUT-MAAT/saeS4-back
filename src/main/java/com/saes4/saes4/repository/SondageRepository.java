package com.saes4.saes4.repository;

import com.saes4.saes4.model.Sondage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SondageRepository extends JpaRepository<Sondage, Long>,
                                        JpaSpecificationExecutor<Sondage> {
}
