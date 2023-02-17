package com.saes4.saes4.repository;

import com.saes4.saes4.model.entities.Sondage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SondageRepository extends JpaRepository<Sondage, Long>,
                                        JpaSpecificationExecutor<Sondage> {

    boolean existsByMail(@Param("mail") String mail);
}
