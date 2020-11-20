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
public class LoginService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    YearlyEmissionRepository yearlyEmissionRepository;

    String currentUser;

    public boolean login(String username) {
        if (accountRepository.findByUsername(username) != null) {
            currentUser = username;
            return true;
        }
        return false;
    }

    public boolean createAccount(String username) {
        if (accountRepository.findByUsername(username) == null) {
            Account account = new Account();
            account.setUsername(username);
            YearlyEmission yearlyEmission = new YearlyEmission();
            yearlyEmission.setElectricity(1000);
            yearlyEmission.setEmission(1);
            yearlyEmission.setHouseSize(1);
            yearlyEmission.setPopulation(1);
            yearlyEmission.setHouse(House.APARTMENT);
            yearlyEmissionRepository.save(yearlyEmission);
            account.setYearlyEmission(yearlyEmission);
            accountRepository.save(account);
            return true;
        }
        return false;
    }

    public String getCurrentUser() {
        return currentUser;
    }
}
