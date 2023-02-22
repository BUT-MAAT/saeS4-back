package com.saes4.saes4.service;

import com.saes4.saes4.model.dto.StatistiquesDTO;
import com.saes4.saes4.repository.StatistiquesGeneralesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StatistiquesService {

    @Autowired
    private StatistiquesGeneralesRepository statistiquesGeneralesRepository;

    public StatistiquesDTO getStatistiques() {
        return null;
    }

}
