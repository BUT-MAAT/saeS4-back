package com.saes4.saes4.controller;

import com.saes4.saes4.model.UniqueData;
import com.saes4.saes4.model.UniqueResponse;
import com.saes4.saes4.service.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/utils")
public class UtilsController {

    @Autowired
    private UtilsService utilsService;

    @PostMapping("/mail_valide")
    public UniqueResponse<Boolean> mailIsValide(@RequestBody UniqueData<String> mail) {
        return utilsService.mailIsValide(mail);
    }
}
