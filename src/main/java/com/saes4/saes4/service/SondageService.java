package com.saes4.saes4.service;

import com.saes4.saes4.mapper.SondageMapper;
import com.saes4.saes4.model.entities.Sondage;
import com.saes4.saes4.model.dto.SondageDTO;
import com.saes4.saes4.repository.*;
import com.saes4.saes4.service.triggers.SondageTrigger;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Transactional
public class SondageService {

    @Autowired
    private SondageRepository sondageRepository;

    @Autowired
    private SondageMapper sondageMapper;

    @Autowired
    private SondageTrigger sondageTrigger;

    public SondageDTO createSondage(SondageDTO reponse) {
        Sondage reponseEntity = sondageMapper.sondageDTOToSondage(reponse);
        sondageRepository.save(reponseEntity);
        sondageTrigger.triggerStatistiquesGenerales();
        return reponse;
    }
}
