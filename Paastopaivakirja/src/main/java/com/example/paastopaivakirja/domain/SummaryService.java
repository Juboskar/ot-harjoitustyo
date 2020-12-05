package com.example.paastopaivakirja.domain;

import com.example.paastopaivakirja.dao.AccountRepository;
import com.example.paastopaivakirja.dao.ConsumptionRepository;
import com.example.paastopaivakirja.dao.FoodEmissionRepository;
import com.example.paastopaivakirja.dao.TrafficEmissionRepository;
import com.example.paastopaivakirja.model.Account;
import com.example.paastopaivakirja.model.Consumption;
import com.example.paastopaivakirja.model.FoodEmission;
import com.example.paastopaivakirja.model.TrafficEmission;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Calculates summarys of different emission types
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

    /**
     * @param username username of user
     * @return total emissions of user without static yearly emission
     */
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
}
