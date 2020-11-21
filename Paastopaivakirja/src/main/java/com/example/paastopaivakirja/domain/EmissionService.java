package com.example.paastopaivakirja.domain;

import com.example.paastopaivakirja.dao.AccountRepository;
import com.example.paastopaivakirja.model.Account;
import com.example.paastopaivakirja.model.YearlyEmission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.paastopaivakirja.dao.YearlyEmissionRepository;

/**
 *
 * @author Oskari
 */
@Service
public class EmissionService {

    @Autowired
    YearlyEmissionRepository yearlyEmissionRepository;

    @Autowired
    AccountRepository accountRepository;

    public YearlyEmission findEmissionInfo(String username) {
        return accountRepository.findByUsername(username).getYearlyEmission();
    }

    public void submitNewValues(int houseSize, int population, int electricity,
            int electricityTypeFactor, House house, String username) {
        YearlyEmission emission = accountRepository.findByUsername(username).getYearlyEmission();
        emission.setHouseSize(houseSize);
        emission.setPopulation(population);
        emission.setElectricity(electricity);
        emission.setElectricityTypeFactor(electricityTypeFactor);
        emission.setHouse(house);
        yearlyEmissionRepository.save(emission);
    }

    public int calculateYearlyEmission(String username) {
        YearlyEmission emission = accountRepository.findByUsername(username).getYearlyEmission();
        /*asumisen perustpäästöt, rakentaminen yms*/
        int calculatedBuildingEmission = emission.getHouseSize()
                * emission.getHouse().getEmission();
        /*sähkönkulutuspäästöt*/
        int calculatedElectricityEmission
                = emission.getElectricity() * 281 * emission.getElectricityTypeFactor();
        /*lämmönkulutus (suomen keskimääräinen, kaukolämpö)*/
        int warmingEmission = emission.getHouseSize() * 241 * 267;

        int total = (calculatedBuildingEmission + calculatedElectricityEmission + warmingEmission)
                / emission.getPopulation();
        return total / 1000;
    }
}
