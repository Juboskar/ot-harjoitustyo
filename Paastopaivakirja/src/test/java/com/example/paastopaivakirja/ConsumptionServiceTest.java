package com.example.paastopaivakirja;

import com.example.paastopaivakirja.dao.AccountRepository;
import com.example.paastopaivakirja.dao.ConsumptionRepository;
import com.example.paastopaivakirja.domain.ConsumptionService;
import com.example.paastopaivakirja.model.Account;
import com.example.paastopaivakirja.model.Consumption;
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
public class ConsumptionServiceTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ConsumptionRepository consumptionRepository;

    @Autowired
    ConsumptionService consumptionService;

    @Test
    public void testCheckIfExists() {
        Account account = new Account();
        LocalDate date = LocalDate.of(2020, Month.MARCH, 1);
        account.setUsername("consumption");
        accountRepository.save(account);
        Consumption consumption = new Consumption();
        consumption.setLocalDate(date);
        consumption.setAccount(account);
        consumption.setBooks(1);
        consumption.setClothes(2);
        consumption.setElectronics(3);
        consumption.setFreetime(4);
        consumption.setMiscellaneous(5);
        consumption.setPhone(6);
        consumption.setShoes(7);
        consumptionRepository.save(consumption);

        assertTrue(consumptionService.checkIfExists("consumption", date));
              
        LocalDate fakeDate = LocalDate.of(2020, Month.MARCH, 2);
        assertFalse(consumptionService.checkIfExists("consumption", fakeDate));
        assertFalse(consumptionService.checkIfExists("fakeuser", date));
    }

}
