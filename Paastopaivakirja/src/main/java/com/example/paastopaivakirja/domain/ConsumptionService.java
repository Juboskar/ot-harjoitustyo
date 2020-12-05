package com.example.paastopaivakirja.domain;

import com.example.paastopaivakirja.dao.AccountRepository;
import com.example.paastopaivakirja.dao.ConsumptionRepository;
import com.example.paastopaivakirja.model.Account;
import com.example.paastopaivakirja.model.Consumption;
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

    /**
     * Checks if user has already given dates consumption emission info
     *
     * @param username username of user
     * @param date date of traffic emission info
     * @return true if consumption emission info already exists, false if not
     */
    public boolean checkIfExists(String username, LocalDate date) {
        Account user = accountRepository.findByUsername(username);
        Consumption consumption = consumptionRepository.findByAccountAndLocalDate(user, date);
        return consumption != null;
    }

    /**
     * Checks if user has already given dates consumption emission info, if not
     * returns default values
     *
     * @param username username of user
     * @param date date of food emission info
     * @return Consumption by user and date
     */
    public Consumption findEmissionInfo(String username, LocalDate date) {
        Account user = accountRepository.findByUsername(username);
        Consumption consumption = consumptionRepository.findByAccountAndLocalDate(user, date);
        if (consumption == null) {
            consumption = new Consumption();
            consumption.setAccount(user);
            consumption.setLocalDate(date);
            return setDefaultValues(consumption);
        }
        return consumptionRepository.findByAccountAndLocalDate(user, date);
    }
    
    private Consumption setDefaultValues(Consumption consumption) {
        consumption.setBooks(0);
        consumption.setClothes(0);
        consumption.setElectronics(0);
        consumption.setFreetime(0);
        consumption.setMiscellaneous(0);
        consumption.setPhone(0);
        consumption.setShoes(0);
        return consumption;
    }

    /**
     * Overwrites and saves matching Consumption with given values
     *
     * @param username username of user
     * @param date date of consumption emission info
     * @param clothes euros spent on clothes
     * @param shoes euros spent on shoes
     * @param electronics euros spent on electronics
     * @param books euros spent on books
     * @param freetime euros spent on freetime services
     * @param phone euros spent on phone and internet products
     * @param miscellaneous euros spent on other stuff
     */
    public void submit(String username, LocalDate date, int clothes,
            int shoes, int electronics, int books, int freetime, int phone, int miscellaneous) {
        Account user = accountRepository.findByUsername(username);
        Consumption consumption = consumptionRepository.findByAccountAndLocalDate(user, date);
        if (consumption == null) {
            consumption = new Consumption();
            consumption.setAccount(user);
            consumption.setLocalDate(date);
        }
        consumption.setBooks(books);
        consumption.setClothes(clothes);
        consumption.setElectronics(electronics);
        consumption.setFreetime(freetime);
        consumption.setMiscellaneous(miscellaneous);
        consumption.setPhone(phone);
        consumption.setShoes(shoes);
        consumptionRepository.save(consumption);
    }

    /**
     * Calculates selected users emission caused by consumption of selected day;
     * consumed euros * categorys semission factor (co2/€)
     *
     * @param username username of user
     * @param date date of consumption emission info
     * @return calculated summary of consumption emission g co2 eq
     */
    public int calculateTodaysConsumptionEmission(String username, LocalDate date) {
        Account user = accountRepository.findByUsername(username);
        Consumption consumption = consumptionRepository.findByAccountAndLocalDate(user, date);
        if (consumption == null) {
            return 0;
        }
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

    /**
     * Finds a list of days of users saved emission caused by consumption
     *
     * @param username username of user
     * @param dateNow current date
     * @return list of already saved Consumption dates
     */
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

    /**
     * Selects a date
     *
     * @param date date that should be selected
     */
    public void setSelectedDate(LocalDate date) {
        this.selectedDate = date;
    }

    /**
     * @return current selected day
     */
    public LocalDate getSelectedDate() {
        return selectedDate;
    }
}
