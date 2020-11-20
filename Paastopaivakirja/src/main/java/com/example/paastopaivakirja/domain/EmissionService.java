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

    
    /*TODO: muutettava niin, että nämä tiedot haetaan yhdellä kertaa,
    tässä ei ole mitään järkeä*/
    
    public YearlyEmission findEmissionInfo(String username) {
        Account account = accountRepository.findByUsername(username);
        return account.getYearlyEmission();
    }

    public void submitNewValues(int houseSize, int population, int electricity, House house, String username) {
        Account account = accountRepository.findByUsername(username);
        YearlyEmission emission = account.getYearlyEmission();
        emission.setHouseSize(houseSize);
        emission.setPopulation(population);
        emission.setElectricity(electricity);
        emission.setHouse(house);
        yearlyEmissionRepository.save(emission);
    }
}
