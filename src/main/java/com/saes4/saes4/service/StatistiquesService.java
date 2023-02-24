package com.saes4.saes4.service;

import com.saes4.saes4.mapper.StatistiquesGeneralesMapper;
import com.saes4.saes4.model.dto.statistiques.StatistiquesDTO;
import com.saes4.saes4.model.dto.StatistiquesGeneralesDTO;
import com.saes4.saes4.model.entities.StatistiquesGenerales;
import com.saes4.saes4.repository.StatistiquesGeneralesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StatistiquesService {

    @Autowired
    private StatistiquesGeneralesRepository statistiquesGeneralesRepository;

    @Autowired
    StatistiquesGeneralesMapper statistiquesGeneralesMapper;

    public StatistiquesDTO getLastStatistiques() {
        StatistiquesGenerales statistiquesGenerales = statistiquesGeneralesRepository.findTopByOrderByTimelogDesc();
        StatistiquesGeneralesDTO statistiquesGeneralesDTO = statistiquesGeneralesMapper.complementStatistiquesGeneralesToStatistiquesGeneralesDTO(
                statistiquesGenerales,
                statistiquesGeneralesMapper.statistiquesGeneralesToStatistiquesGeneralesDTO(statistiquesGenerales)
        );

        StatistiquesDTO statistiquesDTO = new StatistiquesDTO();
        statistiquesDTO.setStatistiques_generales(statistiquesGeneralesDTO);

        return statistiquesDTO;
    }

}
