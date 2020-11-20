package com.example.paastopaivakirja.domain;

import com.example.paastopaivakirja.dao.AccountRepository;
import com.example.paastopaivakirja.model.Account;
import com.example.paastopaivakirja.model.YearlyEmission;
import javax.transaction.Transactional;

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

    @Transactional
    public int findUserHouseSize(String username) {
        Account account = accountRepository.findByUsername(username);
        return account.getYearlyEmission().getHouseSize();
    }

    @Transactional
    public int findUserPopulation(String username) {
        Account account = accountRepository.findByUsername(username);
        return account.getYearlyEmission().getPopulation();
    }

    public void submitNewValues(int houseSize, int population, String username) {
        Account account = accountRepository.findByUsername(username);
        YearlyEmission emission = account.getYearlyEmission();
        emission.setHouseSize(houseSize);
        emission.setPopulation(population);
        yearlyEmissionRepository.save(emission);
    }
}
