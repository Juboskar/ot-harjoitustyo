package com.example.paastopaivakirja.domain;

import com.example.paastopaivakirja.dao.AccountRepository;
import com.example.paastopaivakirja.dao.FoodEmissionRepository;
import com.example.paastopaivakirja.model.Account;
import com.example.paastopaivakirja.model.FoodEmission;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Calculates and handles users food emission info
 *
 * @author Oskari
 */
@Service
public class FoodService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    FoodEmissionRepository foodEmissionRepository;

    private LocalDate selectedDate;

    /**
     * Checks if user has already given dates food emission info
     *
     * @param username username of user
     * @param date date of food emission info
     * @return true if food emission info already exists, false if not
     */
    public boolean checkIfExists(String username, LocalDate date) {
        Account user = accountRepository.findByUsername(username);
        FoodEmission foodEmission = foodEmissionRepository.findByAccountAndLocalDate(user, date);
        return foodEmission != null;
    }

    /**
     * Checks if user has already given dates food emission info, if not,
     * returns default values
     *
     * @param username username of user
     * @param date date of food emission info
     * @return FoodEmission by user and date
     */
    public FoodEmission findEmissionInfo(String username, LocalDate date) {
        Account user = accountRepository.findByUsername(username);
        FoodEmission foodEmission = foodEmissionRepository.findByAccountAndLocalDate(user, date);
        if (foodEmission == null) {
            foodEmission = new FoodEmission();
            foodEmission.setAccount(user);
            foodEmission.setLocalDate(date);
            return setDefaultValues(foodEmission);
        }
        return foodEmissionRepository.findByAccountAndLocalDate(user, date);
    }

    private FoodEmission setDefaultValues(FoodEmission foodEmission) {
        foodEmission.setCow(58);
        foodEmission.setPig(173);
        foodEmission.setFish(86);
        foodEmission.setCheese(43);
        foodEmission.setRice(12);
        foodEmission.setEgg(1);
        foodEmission.setRestaurant(10);
        foodEmission.setMilk(54);
        foodEmission.setVegetable(20);
        return foodEmission;
    }

    /**
     * Overwrites and saves matching FoodEmission with given values
     *
     * @param username username of user
     * @param date date of food emission info
     * @param cow grams of eaten cow products
     * @param pig grams of eaten pig products
     * @param fish grams of eaten fish products
     * @param cheese grams of eaten cheese products
     * @param rice grams of eaten rice
     * @param egg number of eaten eggs
     * @param restaurant euros spend in restaurants
     * @param milk grams of eaten milk products
     * @param vegetable grams of eaten greenhouse plants
     */
    public void submit(String username, LocalDate date, int cow, int pig, int fish,
            int cheese, int rice, int egg, int restaurant, int milk, int vegetable) {
        Account user = accountRepository.findByUsername(username);
        FoodEmission foodEmission = foodEmissionRepository.findByAccountAndLocalDate(user, date);
        if (foodEmission == null) {
            foodEmission = new FoodEmission();
            foodEmission.setAccount(user);
            foodEmission.setLocalDate(date);
        }
        foodEmission.setCow(cow);
        foodEmission.setPig(pig);
        foodEmission.setFish(fish);
        foodEmission.setCheese(cheese);
        foodEmission.setRice(rice);
        foodEmission.setEgg(egg);
        foodEmission.setRestaurant(restaurant);
        foodEmission.setMilk(milk);
        foodEmission.setVegetable(vegetable);
        foodEmissionRepository.save(foodEmission);
    }

    /**
     * Calculates selected users emission caused by food of selected day; Food
     * weight * food types emission factor (co2/g); Eggs weight is approximately
     * 60g/egg.
     *
     * @param username username of user
     * @param date date of food emission info
     * @return calculated summary of food emission g co2 eq
     */
    public int calculateTodaysFoodEmission(String username, LocalDate date) {
        Account user = accountRepository.findByUsername(username);
        FoodEmission foodEmission = foodEmissionRepository.findByAccountAndLocalDate(user, date);
        if (foodEmission == null) {
            return 0;
        }
        int calculated = 0;
        calculated += foodEmission.getCow() * 15;
        calculated += foodEmission.getPig() * 5;
        calculated += foodEmission.getCheese() * 10;
        calculated += foodEmission.getEgg() * 60 * (5 / 2);
        calculated += foodEmission.getFish() * (3 / 2);
        calculated += foodEmission.getMilk() * 1;
        calculated += foodEmission.getRice() * 5;
        calculated += foodEmission.getVegetable() * 5;
        calculated += foodEmission.getRestaurant() * 140;
        return calculated;
    }

    /**
     * Finds a list of days of users saved emission caused by food
     *
     * @param username username of user
     * @param dateNow current date
     * @return list of already saved FoodEmission dates
     */
    public List<LocalDate> findFilledDays(String username, LocalDate dateNow) {
        Account user = accountRepository.findByUsername(username);
        LocalDate startDate = user.getStartDate();
        List<LocalDate> filled = new ArrayList<>();
        long daysBetween = DAYS.between(startDate, dateNow);
        for (int i = 0; i < daysBetween; i++) {
            LocalDate date = startDate.plusDays(i);
            if (foodEmissionRepository.findByAccountAndLocalDate(user, date) != null) {
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
