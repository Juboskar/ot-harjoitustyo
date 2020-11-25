package com.example.paastopaivakirja.domain;

import com.example.paastopaivakirja.dao.AccountRepository;
import com.example.paastopaivakirja.dao.ConsumptionRepository;
import com.example.paastopaivakirja.model.Account;
import com.example.paastopaivakirja.model.Consumption;
import com.example.paastopaivakirja.model.FoodEmission;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Oskari
 */
@Service
public class ConsumptionService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ConsumptionRepository consumptionRepository;

    public boolean checkIfExists(String username, LocalDate date) {
        Account user = accountRepository.findByUsername(username);
        Consumption consumption = consumptionRepository.findByAccountAndLocalDate(user, date);
        return consumption != null;
    }

    public Consumption findEmissionInfo(String username, LocalDate date) {
        Account user = accountRepository.findByUsername(username);
        Consumption consumption = consumptionRepository.findByAccountAndLocalDate(user, date);
        if (consumption == null) {
            consumption = new Consumption();
            consumption.setAccount(user);
            consumption.setLocalDate(date);
            setDefaultValues(consumption);
        }
        return consumptionRepository.findByAccountAndLocalDate(user, date);
    }
    
    public void setDefault(String username, LocalDate date){
     Account user = accountRepository.findByUsername(username);
        Consumption consumption = consumptionRepository.findByAccountAndLocalDate(user, date);
        setDefaultValues(consumption);
    }

    private void setDefaultValues(Consumption consumption) {
        consumption.setBooks(0);
        consumption.setClothes(0);
        consumption.setElectronics(0);
        consumption.setFreetime(0);
        consumption.setMiscellaneous(0);
        consumption.setPhone(0);
        consumption.setShoes(0);
        consumptionRepository.save(consumption);
    }

    public void submit(String username, LocalDate date, int clothes,
            int shoes, int electronics, int books, int freetime, int phone, int miscellaneous) {
        Account user = accountRepository.findByUsername(username);
        Consumption consumption = consumptionRepository.findByAccountAndLocalDate(user, date);
        consumption.setBooks(books);
        consumption.setClothes(clothes);
        consumption.setElectronics(electronics);
        consumption.setFreetime(freetime);
        consumption.setMiscellaneous(miscellaneous);
        consumption.setPhone(phone);
        consumption.setShoes(shoes);
        consumptionRepository.save(consumption);
    }

    public int calculateTodaysConsumptionEmission(String username, LocalDate date) {
        return 0;
    }
}
