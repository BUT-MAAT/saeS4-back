package com.saes4.saes4.service;

import com.saes4.saes4.mapper.AlimentMapper;
import com.saes4.saes4.mapper.StatistiquesMapper;
import com.saes4.saes4.model.dto.StatistiquesDTO;
import com.saes4.saes4.model.dto.statistiques.AlimentCountDTO;
import com.saes4.saes4.model.entities.Aliment;
import com.saes4.saes4.model.entities.Sondage;
import com.saes4.saes4.model.entities.Statistiques;
import com.saes4.saes4.repository.SondageRepository;
import com.saes4.saes4.repository.StatistiquesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class StatistiquesService {
    @Autowired
    AlimentMapper alimentMapper;
    @Autowired
    private StatistiquesRepository statistiquesRepository;

    @Autowired
    StatistiquesMapper statistiquesMapper;

    @Autowired
    SondageRepository sondageRepository;

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
    public List<AlimentCountDTO> getMostConsumedAlimentsByDepartment(String department,Long size) {
        List<AlimentCountDTO> count = new LinkedList<>();
        List<Sondage> sondages = sondageRepository.findAll();
        sondages = sondages.stream()
                .filter(sondage -> sondage.getCode_postal().substring(0,2).equals((department)))
                .toList();


        Iterator<AlimentCountDTO> it;
        AlimentCountDTO alimentCountDTO;
        boolean found = false;
        for(Sondage sondage : sondages){
            for(Aliment aliment : sondage.getListe_aliments()){
                it = count.iterator();
                while(it.hasNext()){
                    alimentCountDTO = it.next();
                    if(alimentCountDTO.getId_aliment().equals(aliment.getId_aliment())) {
                        alimentCountDTO.setNbChoisi((Long) (alimentCountDTO.getNbChoisi() + 1));
                        found = true;
                        break;
                    }
                }
                if(!found) {
                    alimentCountDTO = alimentMapper.alimentToAlimentCountDTONoValeursNutritives(aliment);
                    alimentCountDTO.setNbChoisi((long) 1);
                    count.add(alimentCountDTO);
                }
                found = false;
            }
        }

        Collections.sort(count);
        if(size == null)
            return count;
        List<AlimentCountDTO> result = count.size() < size.intValue() ? count : count.subList(0,size.intValue());
        return result;
    }
}
