package com.example.paastopaivakirja.domain;

import com.example.paastopaivakirja.dao.AccountRepository;
import com.example.paastopaivakirja.dao.ConsumptionRepository;
import com.example.paastopaivakirja.model.Account;
import com.example.paastopaivakirja.model.Consumption;
import com.example.paastopaivakirja.model.FoodEmission;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Calculates and handles users consumption emission info
 *
 * @author Oskari
 */
@Service
public class ConsumptionService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ConsumptionRepository consumptionRepository;

    private LocalDate selectedDate;

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

    public void setDefault(String username, LocalDate date) {
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
        Account user = accountRepository.findByUsername(username);
        Consumption consumption = consumptionRepository.findByAccountAndLocalDate(user, date);
        if (consumption == null) {
            return 0;
        }
        /*muunnetaan senteiksi ja kerrotaan kulutettu rahamäärä kyseisen kategorian hiilikertoimella*/
        int calculated = 0;
        calculated += consumption.getBooks() * 320;
        calculated += consumption.getClothes() * 470;
        calculated += consumption.getElectronics() * 890;
        calculated += consumption.getFreetime() * 390;
        calculated += consumption.getMiscellaneous() * 240;
        calculated += consumption.getPhone() * 280;
        calculated += consumption.getShoes() * 290;
        return calculated;
    }

    public List<LocalDate> findFilledDays(String username, LocalDate dateNow) {
        Account user = accountRepository.findByUsername(username);
        LocalDate startDate = user.getStartDate();
        List<LocalDate> filled = new ArrayList<>();
        long daysBetween = DAYS.between(startDate, dateNow);
        for (int i = 0; i < daysBetween; i++) {
            LocalDate date = startDate.plusDays(i);
            if (consumptionRepository.findByAccountAndLocalDate(user, date) != null) {
                filled.add(date);
            }
        }
        return filled;
    }

    public void setSelectedDate(LocalDate date) {
        this.selectedDate = date;
    }

    public LocalDate getSelectedDate() {
        return selectedDate;
    }
}
