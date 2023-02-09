package com.saes4.saes4.service;

import com.saes4.saes4.model.Aliment;
import com.saes4.saes4.repository.AlimentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AlimentService {
    @Autowired
    AlimentRepository alimentRepository;
    public List<Aliment> getAllAliments() {
        return alimentRepository.findAll();
    }
}
