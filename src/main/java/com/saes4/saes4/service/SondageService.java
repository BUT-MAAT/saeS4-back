package com.saes4.saes4.service;

import com.saes4.saes4.mapper.SondageMapper;
import com.saes4.saes4.model.entities.Sondage;
import com.saes4.saes4.model.dto.SondageDTO;
import com.saes4.saes4.repository.SondageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SondageService {

    @Autowired
    private SondageRepository sondageRepository;

    @Autowired
    private SondageMapper sondageMapper;

    public SondageDTO createSondage(SondageDTO reponse) {
        Sondage reponseEntity = sondageMapper.sondageDTOToSondage(reponse);
        sondageRepository.save(reponseEntity);
        return reponse;
    }
}
