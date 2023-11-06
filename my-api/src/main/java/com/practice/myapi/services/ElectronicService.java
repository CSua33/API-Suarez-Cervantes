package com.practice.myapi.services;

import com.practice.myapi.model.electronicProduct;
import com.practice.myapi.repositories.ElectronicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElectronicService {
    private final ElectronicRepository electronicRepository;

    public ElectronicService(ElectronicRepository electronicRepository) {
        this.electronicRepository = electronicRepository;
    }

    public List<electronicProduct> getAlLElectronics(){
        return electronicRepository.getAlLElectronics();
    }

    public String createElectronic(electronicProduct newElectronic) {
        return electronicRepository.createElectronic(newElectronic);
    }
}
