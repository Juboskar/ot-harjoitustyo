/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.paastopaivakirja.domain;

import com.example.paastopaivakirja.dao.AccountRepository;
import com.example.paastopaivakirja.dao.ConsumptionRepository;
import com.example.paastopaivakirja.dao.FoodEmissionRepository;
import com.example.paastopaivakirja.dao.TrafficEmissionRepository;
import com.example.paastopaivakirja.model.Account;
import com.example.paastopaivakirja.model.Consumption;
import com.example.paastopaivakirja.model.FoodEmission;
import com.example.paastopaivakirja.model.TrafficEmission;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Oskari
 */
@Service
public class SummaryService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    FoodEmissionRepository foodEmissionRepository;

    @Autowired
    TrafficEmissionRepository trafficEmissionRepository;

    @Autowired
    ConsumptionRepository consumptionRepository;

    @Autowired
    FoodService foodService;

    @Autowired
    TrafficService trafficService;

    @Autowired
    ConsumptionService consumptionService;

    /*TODO: tästä tehokkaampi tietokantojen osalta*/
    public int calculateSummary(String username) {
        Account user = accountRepository.findByUsername(username);
        List<FoodEmission> foods = foodEmissionRepository.findByAccount(user);
        List<TrafficEmission> traffics = trafficEmissionRepository.findByAccount(user);
        List<Consumption> consumptions = consumptionRepository.findByAccount(user);
        int total = 0;
        total = foods.stream().map((food)
                -> foodService.calculateTodaysFoodEmission(username, food.getLocalDate()))
                .reduce(total, Integer::sum);
        total = consumptions.stream().map((consumption)
                -> consumptionService.calculateTodaysConsumptionEmission(username, consumption.getLocalDate()))
                .reduce(total, Integer::sum);
        total = traffics.stream().map((traffic)
                -> trafficService.calculateTodaysTrafficEmission(username, traffic.getLocalDate()))
                .reduce(total, Integer::sum);
        return total;
    }

    /*tämä metodi ei ole vielä käytössä*/
    public List<LocalDate> findUnfilledDays(String username, LocalDate dateNow) {
        Account user = accountRepository.findByUsername(username);
        LocalDate startDate = user.getStartDate();
        List<LocalDate> unfilled = new ArrayList<>();
        long daysBetween = DAYS.between(startDate, dateNow);
        for (int i = 0; i < daysBetween; i++) {
            LocalDate date = startDate.plusDays(i);
            if (foodEmissionRepository.findByAccountAndLocalDate(user, date) == null
                    || trafficEmissionRepository.findByAccountAndLocalDate(user, date) == null
                    || consumptionRepository.findByAccountAndLocalDate(user, date) == null) {
                unfilled.add(date);
            }
        }
        return unfilled;
    }
}
