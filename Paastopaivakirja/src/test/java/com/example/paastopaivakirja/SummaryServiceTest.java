package com.example.paastopaivakirja;

import com.example.paastopaivakirja.dao.AccountRepository;
import com.example.paastopaivakirja.dao.ConsumptionRepository;
import com.example.paastopaivakirja.dao.FoodEmissionRepository;
import com.example.paastopaivakirja.dao.TrafficEmissionRepository;
import com.example.paastopaivakirja.domain.SummaryService;
import com.example.paastopaivakirja.model.Account;
import com.example.paastopaivakirja.model.Consumption;
import com.example.paastopaivakirja.model.FoodEmission;
import com.example.paastopaivakirja.model.TrafficEmission;
import java.time.LocalDate;
import java.time.Month;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Oskari
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class SummaryServiceTest {

    @Autowired
    SummaryService summaryService;

    @Autowired
    TrafficEmissionRepository trafficEmissionRepository;

    @Autowired
    FoodEmissionRepository foodEmissionRepository;
    
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ConsumptionRepository consumptionRepository;

    @Test
    public void testCalculateSummary() {
        Account account = new Account();
        LocalDate dateNow = LocalDate.of(2020, Month.MARCH, 7);
        LocalDate startDate = LocalDate.of(2020, Month.MARCH, 1);
        account.setUsername("summaryTest");
        account.setStartDate(startDate);
        accountRepository.save(account);

        FoodEmission foodEmission = new FoodEmission();
        foodEmission.setLocalDate(dateNow.minusDays(1));
        foodEmission.setAccount(account);
        foodEmission.setCheese(1);
        foodEmission.setCow(2);
        foodEmission.setEgg(3);
        foodEmission.setFish(4);
        foodEmission.setMilk(5);
        foodEmission.setPig(6);
        foodEmission.setRestaurant(7);
        foodEmission.setRice(8);
        foodEmission.setVegetable(9);
        foodEmissionRepository.save(foodEmission);

        FoodEmission foodEmission2 = new FoodEmission();
        foodEmission2.setLocalDate(dateNow.minusDays(2));
        foodEmission2.setAccount(account);
        foodEmission2.setCheese(1);
        foodEmission2.setCow(2);
        foodEmission2.setEgg(3);
        foodEmission2.setFish(4);
        foodEmission2.setMilk(5);
        foodEmission2.setPig(6);
        foodEmission2.setRestaurant(7);
        foodEmission2.setRice(8);
        foodEmission2.setVegetable(9);
        foodEmissionRepository.save(foodEmission2);

        TrafficEmission trafficEmission = new TrafficEmission();
        trafficEmission.setLocalDate(dateNow.minusDays(1));
        trafficEmission.setAccount(account);
        trafficEmission.setAirplane(1);
        trafficEmission.setCar(2);
        trafficEmission.setLongDistanceBus(3);
        trafficEmission.setLongDistanceTrain(4);
        trafficEmission.setMetro(5);
        trafficEmission.setShip(6);
        trafficEmission.setShortDistanceBus(7);
        trafficEmission.setShortDistanceTrain(8);
        trafficEmission.setTram(9);
        trafficEmissionRepository.save(trafficEmission);

        TrafficEmission trafficEmission2 = new TrafficEmission();
        trafficEmission2.setLocalDate(dateNow.minusDays(2));
        trafficEmission2.setAccount(account);
        trafficEmission2.setAirplane(1);
        trafficEmission2.setCar(2);
        trafficEmission2.setLongDistanceBus(3);
        trafficEmission2.setLongDistanceTrain(4);
        trafficEmission2.setMetro(5);
        trafficEmission2.setShip(6);
        trafficEmission2.setShortDistanceBus(7);
        trafficEmission2.setShortDistanceTrain(8);
        trafficEmission2.setTram(9);
        trafficEmissionRepository.save(trafficEmission2);

        Consumption consumptionTest = new Consumption();
        consumptionTest.setLocalDate(dateNow.minusDays(1));
        consumptionTest.setAccount(account);
        consumptionTest.setBooks(1);
        consumptionTest.setClothes(2);
        consumptionTest.setElectronics(3);
        consumptionTest.setFreetime(4);
        consumptionTest.setMiscellaneous(5);
        consumptionTest.setPhone(6);
        consumptionTest.setShoes(7);
        consumptionRepository.save(consumptionTest);

        Consumption consumptionTest2 = new Consumption();
        consumptionTest2.setLocalDate(dateNow.minusDays(2));
        consumptionTest2.setAccount(account);
        consumptionTest2.setBooks(1);
        consumptionTest2.setClothes(2);
        consumptionTest2.setElectronics(3);
        consumptionTest2.setFreetime(4);
        consumptionTest2.setMiscellaneous(5);
        consumptionTest2.setPhone(6);
        consumptionTest2.setShoes(7);
        consumptionRepository.save(consumptionTest2);

        assertEquals(28518, summaryService.calculateSummary("summaryTest"));
    }
}
