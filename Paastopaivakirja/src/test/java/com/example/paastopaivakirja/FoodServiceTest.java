package com.example.paastopaivakirja;

import com.example.paastopaivakirja.dao.AccountRepository;
import com.example.paastopaivakirja.dao.FoodEmissionRepository;
import com.example.paastopaivakirja.domain.FoodService;
import com.example.paastopaivakirja.model.Account;
import com.example.paastopaivakirja.model.FoodEmission;
import java.time.LocalDate;
import java.time.Month;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
public class FoodServiceTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    FoodService foodService;

    @Autowired
    FoodEmissionRepository foodEmissionRepository;

    @Test
    public void testCheckIfExists() {
        Account account = new Account();
        LocalDate date = LocalDate.of(2020, Month.MARCH, 1);
        account.setUsername("food");
        accountRepository.save(account);
        FoodEmission foodEmission = new FoodEmission();
        foodEmission.setLocalDate(date);
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

        assertTrue(foodService.checkIfExists("food", date));

        LocalDate fakeDate = LocalDate.of(2020, Month.MARCH, 2);
        assertFalse(foodService.checkIfExists("food", fakeDate));
        assertFalse(foodService.checkIfExists("fakeuser", date));
    }

    @Test
    public void testFindEmissionInfo() {
        LocalDate date = LocalDate.of(2020, Month.MARCH, 3);
        FoodEmission foodEmission = foodService.findEmissionInfo("infouser", date);
        assertEquals(58, foodEmission.getCow());
        assertEquals(173, foodEmission.getPig());
        assertEquals(86, foodEmission.getFish());
        assertEquals(43, foodEmission.getCheese());
        assertEquals(12, foodEmission.getRice());
        assertEquals(1, foodEmission.getEgg());
        assertEquals(10, foodEmission.getRestaurant());
        assertEquals(54, foodEmission.getMilk());
        assertEquals(20, foodEmission.getVegetable());

        Account account = new Account();
        account.setUsername("foodinfouser");
        accountRepository.save(account);

        FoodEmission foodEmissionTest = new FoodEmission();
        foodEmissionTest.setLocalDate(date);
        foodEmissionTest.setAccount(account);
        foodEmissionTest.setCheese(1);
        foodEmissionTest.setCow(2);
        foodEmissionTest.setEgg(3);
        foodEmissionTest.setFish(4);
        foodEmissionTest.setMilk(5);
        foodEmissionTest.setPig(6);
        foodEmissionTest.setRestaurant(7);
        foodEmissionTest.setRice(8);
        foodEmissionTest.setVegetable(9);
        foodEmissionRepository.save(foodEmissionTest);

        FoodEmission foodEmission2 = foodService.findEmissionInfo("foodinfouser", date);
        assertEquals(1, foodEmission2.getCheese());
        assertEquals(2, foodEmission2.getCow());
        assertEquals(3, foodEmission2.getEgg());
        assertEquals(4, foodEmission2.getFish());
        assertEquals(5, foodEmission2.getMilk());
        assertEquals(6, foodEmission2.getPig());
        assertEquals(7, foodEmission2.getRestaurant());
        assertEquals(8, foodEmission2.getRice());
        assertEquals(9, foodEmission2.getVegetable());
    }

    @Test
    public void testSubmitValues() {
        Account account = new Account();
        LocalDate date = LocalDate.of(2020, Month.MARCH, 1);
        account.setUsername("foodsubmit");
        accountRepository.save(account);

        foodService.submit("foodsubmit", date, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        FoodEmission foodTest = foodEmissionRepository.findByAccountAndLocalDate(
                account, date);
        assertEquals(1, foodTest.getCow());
        assertEquals(2, foodTest.getPig());
        assertEquals(3, foodTest.getFish());
        assertEquals(4, foodTest.getCheese());
        assertEquals(5, foodTest.getRice());
        assertEquals(6, foodTest.getEgg());
        assertEquals(7, foodTest.getRestaurant());
        assertEquals(8, foodTest.getMilk());
        assertEquals(9, foodTest.getVegetable());
    }

    @Test
    public void testCalculate() {
        Account account = new Account();
        LocalDate date = LocalDate.of(2020, Month.MARCH, 1);
        account.setUsername("foodcalc");
        accountRepository.save(account);

        FoodEmission foodEmission = new FoodEmission();
        foodEmission.setLocalDate(date);
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

        assertEquals(0, foodService.calculateTodaysFoodEmission("fakeuser", date));
        assertEquals(1504, foodService.calculateTodaysFoodEmission("foodcalc", date));
    }

    @Test
    public void testFilledDays() {
        Account account = new Account();
        LocalDate dateNow = LocalDate.of(2020, Month.MARCH, 7);
        LocalDate startDate = LocalDate.of(2020, Month.MARCH, 1);
        account.setUsername("daytestFood");
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

        assertEquals(2, foodService.findFilledDays("daytestFood", dateNow).size());
        assertTrue(foodService.findFilledDays("daytestFood", dateNow).contains(dateNow.minusDays(1)));
        assertTrue(foodService.findFilledDays("daytestFood", dateNow).contains(dateNow.minusDays(2)));

    }

    @Test
    public void testSelectDates() {
        LocalDate date = LocalDate.of(2020, Month.MARCH, 1);
        foodService.setSelectedDate(date);

        assertEquals(date, foodService.getSelectedDate());
    }
}
