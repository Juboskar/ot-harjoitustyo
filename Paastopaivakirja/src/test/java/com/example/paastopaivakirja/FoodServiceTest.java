package com.example.paastopaivakirja;

import com.example.paastopaivakirja.dao.AccountRepository;
import com.example.paastopaivakirja.dao.FoodEmissionRepository;
import com.example.paastopaivakirja.domain.FoodService;
import com.example.paastopaivakirja.model.Account;
import com.example.paastopaivakirja.model.FoodEmission;
import java.time.LocalDate;
import java.time.Month;
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
    FoodEmissionRepository foodEmissionRepository;

    @Autowired
    FoodService foodService;

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

}
