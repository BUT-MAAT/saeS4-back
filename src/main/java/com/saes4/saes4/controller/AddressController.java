package com.saes4.saes4.controller;

import com.saes4.saes4.model.dto.AddressDTO;
import com.saes4.saes4.model.dto.CategorieDTO;
import com.saes4.saes4.model.dto.FeatureCollectionDTO;
import com.saes4.saes4.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    AddressService addressService;
    @GetMapping("/getaddress/{addressSubString}")
    public List<AddressDTO> getAddressBySubstring(@PathVariable(value = "addressSubString") final String address) {
        return addressService.getAddressBySubstring(address);
    }

}
