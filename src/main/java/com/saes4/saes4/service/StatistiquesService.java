package com.saes4.saes4.service;

import com.saes4.saes4.mapper.StatistiquesMapper;
import com.saes4.saes4.model.dto.StatistiquesDTO;
import com.saes4.saes4.model.entities.Statistiques;
import com.saes4.saes4.repository.StatistiquesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StatistiquesService {

    @Autowired
    private StatistiquesRepository statistiquesRepository;

    @Autowired
    StatistiquesMapper statistiquesMapper;

    public StatistiquesDTO getLastStatistiques() {
        Statistiques statistiques = statistiquesRepository.findTopByOrderByTimelogDesc();
        StatistiquesDTO statistiquesDTO;
        if (statistiques == null) {
            statistiquesDTO = new StatistiquesDTO();
            statistiquesDTO.setNombre_reponses((long) 0);
            return statistiquesDTO;
        }
        statistiquesDTO = statistiquesMapper.complementStatistiquesGeneralesToStatistiquesGeneralesDTO(
                statistiques,
                statistiquesMapper.statistiquesGeneralesToStatistiquesGeneralesDTO(statistiques)
        );
        return statistiquesDTO;
    }

}
