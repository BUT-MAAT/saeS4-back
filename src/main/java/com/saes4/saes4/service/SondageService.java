package com.saes4.saes4.service;

import com.saes4.saes4.model.Sondage;
import com.saes4.saes4.repository.SondageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SondageService {

    @Autowired
    private SondageRepository sondageRepository;

    public Sondage createSondage(Sondage reponse) {
        return sondageRepository.save(reponse);
    }
}
