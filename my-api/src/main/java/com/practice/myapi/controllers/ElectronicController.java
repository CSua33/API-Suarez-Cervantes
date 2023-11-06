package com.practice.myapi.controllers;

import com.practice.myapi.model.electronicProduct;
import com.practice.myapi.services.ElectronicService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ElectronicController {
    private final ElectronicService electronicService;

    public ElectronicController(ElectronicService electronicService) {
        this.electronicService = electronicService;
    }

    @GetMapping("/electronic")
    public List<electronicProduct> getAllElectronics(){
        return electronicService.getAlLElectronics();
    }

    @PostMapping("/electronic")
    public String insertElectronic(@RequestBody electronicProduct newElectronic){
        return electronicService.createElectronic(newElectronic);
    }
}
