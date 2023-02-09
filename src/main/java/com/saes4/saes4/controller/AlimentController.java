package com.saes4.saes4.controller;

import com.saes4.saes4.model.Aliment;
import com.saes4.saes4.service.AlimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/aliment")
public class AlimentController {
    @Autowired
    AlimentService alimentService;

    @GetMapping("/all")
    public List<Aliment> getAllAliment() { return alimentService.getAllAliments(); }
}
