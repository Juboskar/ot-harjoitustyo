package com.example.paastopaivakirja.domain;

import com.example.paastopaivakirja.dao.AccountRepository;
import com.example.paastopaivakirja.dao.FoodEmissionRepository;
import com.example.paastopaivakirja.model.Account;
import com.example.paastopaivakirja.model.FoodEmission;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Oskari
 */
@Service
public class FoodService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    FoodEmissionRepository foodEmissionRepository;

    public boolean checkIfExists(String username, LocalDate date) {
        Account user = accountRepository.findByUsername(username);
        FoodEmission foodEmission = foodEmissionRepository.findByAccountAndLocalDate(user, date);
        return foodEmission != null;
    }

    public FoodEmission findEmissionInfo(String username, LocalDate date) {
        Account user = accountRepository.findByUsername(username);
        FoodEmission foodEmission = foodEmissionRepository.findByAccountAndLocalDate(user, date);
        if (foodEmission == null) {
            foodEmission = new FoodEmission();
            foodEmission.setAccount(user);
            foodEmission.setLocalDate(date);
            setDefaultValues(foodEmission);
        }
        return foodEmissionRepository.findByAccountAndLocalDate(user, date);
    }

    public void setDefault(String username, LocalDate date) {
        Account user = accountRepository.findByUsername(username);
        FoodEmission foodEmission = foodEmissionRepository.findByAccountAndLocalDate(user, date);
        setDefaultValues(foodEmission);
    }

    private void setDefaultValues(FoodEmission foodEmission) {
        foodEmission.setCow(58);
        foodEmission.setPig(173);
        foodEmission.setFish(86);
        foodEmission.setCheese(43);
        foodEmission.setRice(12);
        foodEmission.setEgg(1);
        foodEmission.setRestaurant(10);
        foodEmission.setMilk(54);
        foodEmission.setVegetable(20);
        foodEmissionRepository.save(foodEmission);
    }

    public void submit(String username, LocalDate date, int cow, int pig, int fish,
            int cheese, int rice, int egg, int restaurant, int milk, int vegetable) {
        Account user = accountRepository.findByUsername(username);
        FoodEmission foodEmission = foodEmissionRepository.findByAccountAndLocalDate(user, date);
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

    public int calculateTodaysFoodEmission(String username, LocalDate date) {
        Account user = accountRepository.findByUsername(username);
        FoodEmission foodEmission = foodEmissionRepository.findByAccountAndLocalDate(user, date);
        if (foodEmission == null) {
            return 0;
        }
        /* Lasketaan jokaisen tuotteen päästöt kunkin ruoka-aineen hiilikertoimella
        ja lisätään lähtöarvoihin. Kananmunan paino lasketaan kertomalla 60g:llä. */
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
}
