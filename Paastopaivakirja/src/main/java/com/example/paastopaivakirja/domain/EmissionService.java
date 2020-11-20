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

    public int findUserHouseSize(String username) {
        Account account = accountRepository.findByUsername(username);
        return account.getYearlyEmission().getHouseSize();
    }

    public int findUserPopulation(String username) {
        Account account = accountRepository.findByUsername(username);
        return account.getYearlyEmission().getPopulation();
    }

    public int findUserElectricity(String username) {
        Account account = accountRepository.findByUsername(username);
        return account.getYearlyEmission().getElectricity();
    }

    public void submitNewValues(int houseSize, int population, int electricity, String username) {
        Account account = accountRepository.findByUsername(username);
        YearlyEmission emission = account.getYearlyEmission();
        emission.setHouseSize(houseSize);
        emission.setPopulation(population);
        emission.setElectricity(electricity);
        yearlyEmissionRepository.save(emission);
    }
}
