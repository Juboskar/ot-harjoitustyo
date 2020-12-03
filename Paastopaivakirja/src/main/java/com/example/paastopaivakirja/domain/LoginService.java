package com.example.paastopaivakirja.domain;

import com.example.paastopaivakirja.dao.AccountRepository;
import com.example.paastopaivakirja.model.Account;
import com.example.paastopaivakirja.model.YearlyEmission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.paastopaivakirja.dao.YearlyEmissionRepository;
import java.time.LocalDate;

/**
 * Applications logging in and account create handling service
 * @author Oskari
 */
@Service
public class LoginService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    YearlyEmissionRepository yearlyEmissionRepository;

    private String currentUser;

    /**
     * @param username username given by user
     *
     * @return true if succesfull, false if not
     */
    public boolean login(String username) {
        if (accountRepository.findByUsername(username) != null) {
            currentUser = username;
            return true;
        }
        return false;
    }

    /**
     * @param username username of user
     * @param date date when users account was created
     *
     * @return true if succesfull, false if not
     */
    public boolean createAccount(String username, LocalDate date) {
        if (accountRepository.findByUsername(username) == null) {
            Account account = new Account();
            account.setUsername(username);
            account.setStartDate(date);
            YearlyEmission yearlyEmission = new YearlyEmission();
            yearlyEmission.setElectricity(1900);
            yearlyEmission.setElectricityTypeFactor(1);
            yearlyEmission.setHouseSize(41);
            yearlyEmission.setPopulation(1);
            yearlyEmission.setHouse(House.APARTMENT);
            yearlyEmissionRepository.save(yearlyEmission);
            account.setYearlyEmission(yearlyEmission);
            accountRepository.save(account);
            return true;
        }
        return false;
    }

    /**
     * @return username of latest user logged in
     */
    public String getCurrentUser() {
        return currentUser;
    }

    /**
     * @param username username
     * @return account creation date of user which username was given
     */
    public LocalDate getStartDate(String username) {
        return accountRepository.findByUsername(username).getStartDate();
    }
}
