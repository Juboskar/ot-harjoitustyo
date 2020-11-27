package com.example.paastopaivakirja.domain;

import com.example.paastopaivakirja.dao.AccountRepository;
import com.example.paastopaivakirja.dao.ConsumptionRepository;
import com.example.paastopaivakirja.dao.FoodEmissionRepository;
import com.example.paastopaivakirja.dao.TrafficEmissionRepository;
import com.example.paastopaivakirja.model.YearlyEmission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.paastopaivakirja.dao.YearlyEmissionRepository;
import com.example.paastopaivakirja.model.Account;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ArrayList;
import java.util.List;

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
        /*asumisen peruspäästöt, rakentaminen yms: asunnon koko * asumistyypin co2 -kerroin */
        int calculatedBuildingEmission = emission.getHouseSize()
                * emission.getHouse().getEmission();
        /*sähkönkulutuspäästöt (keskimääräinen, ekosähköllä 0-kerroin): kwh * 281 co2/kwh * eko  */
        int calculatedElectricityEmission
                = emission.getElectricity() * 281 * emission.getElectricityTypeFactor();
        /*lämmönkulutuspäästöt (suomen keskimääräinen, kaukolämpö): asunnon koko * 241kwh * 267co2/kwh*/
        int warmingEmission = emission.getHouseSize() * 241 * 267;
        /*asumisen yhteispäästöt jaettuna talouden henkilömäärällä:*/
        int total = (calculatedBuildingEmission + calculatedElectricityEmission + warmingEmission)
                / emission.getPopulation();
        /*muista kuin pävittäin mitattavista ruoka aineista tulevat keskimääräiset vuosipäästöt*/
        total += 400000;
        return total;
    }
}
