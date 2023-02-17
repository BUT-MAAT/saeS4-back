package com.saes4.saes4.controller;

import com.saes4.saes4.service.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/utils")
public class UtilsController {

    @Autowired
    private UtilsService utilsService;

    @PostMapping("/mail_valide")
    public boolean mailIsValide(@RequestBody String mail) {
        return utilsService.mailIsValide(mail);
    }
}
