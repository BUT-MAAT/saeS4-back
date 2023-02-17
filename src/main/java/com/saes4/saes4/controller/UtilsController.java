package com.saes4.saes4.controller;

import com.saes4.saes4.service.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/utils")
public class UtilsController {

    @Autowired
    private UtilsService utilsService;

    @GetMapping("/mail_valide")
    public boolean mailIsValide(@RequestBody String mail) {
        return utilsService.mailIsValide(mail);
    }
}
