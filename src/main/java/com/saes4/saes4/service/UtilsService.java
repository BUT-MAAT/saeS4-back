package com.saes4.saes4.service;

import com.saes4.saes4.model.UniqueData;
import com.saes4.saes4.model.UniqueResponse;
import com.saes4.saes4.repository.SondageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UtilsService {

    @Autowired
    private SondageRepository sondageRepository;

    public UniqueResponse<Boolean> mailIsValide(UniqueData<String> mail) {
        Boolean isValid = !sondageRepository.existsByMail(mail.getData());
        UniqueResponse<Boolean> response = new UniqueResponse();
        response.setResponse(isValid);
        return response;
    }
}
