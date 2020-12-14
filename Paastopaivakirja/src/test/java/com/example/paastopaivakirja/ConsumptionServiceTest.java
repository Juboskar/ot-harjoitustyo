package com.example.paastopaivakirja;

import com.example.paastopaivakirja.dao.AccountRepository;
import com.example.paastopaivakirja.dao.ConsumptionRepository;
import com.example.paastopaivakirja.domain.ConsumptionService;
import com.example.paastopaivakirja.model.Account;
import com.example.paastopaivakirja.model.Consumption;
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

    @Test
    public void testFindEmissionInfo() {
        LocalDate date = LocalDate.of(2020, Month.MARCH, 3);
        Consumption consumption = consumptionService.findEmissionInfo("infouser", date);
        assertEquals(0, consumption.getBooks());
        assertEquals(0, consumption.getClothes());
        assertEquals(0, consumption.getElectronics());
        assertEquals(0, consumption.getFreetime());
        assertEquals(0, consumption.getMiscellaneous());
        assertEquals(0, consumption.getPhone());
        assertEquals(0, consumption.getShoes());

        Account account = new Account();
        account.setUsername("consumptioninfouser");
        accountRepository.save(account);

        Consumption consumptionTest = new Consumption();
        consumptionTest.setLocalDate(date);
        consumptionTest.setAccount(account);
        consumptionTest.setBooks(1);
        consumptionTest.setClothes(2);
        consumptionTest.setElectronics(3);
        consumptionTest.setFreetime(4);
        consumptionTest.setMiscellaneous(5);
        consumptionTest.setPhone(6);
        consumptionTest.setShoes(7);
        consumptionRepository.save(consumptionTest);

        Consumption consumption2 = consumptionService.findEmissionInfo("consumptioninfouser", date);
        assertEquals(1, consumption2.getBooks());
        assertEquals(2, consumption2.getClothes());
        assertEquals(3, consumption2.getElectronics());
        assertEquals(4, consumption2.getFreetime());
        assertEquals(5, consumption2.getMiscellaneous());
        assertEquals(6, consumption2.getPhone());
        assertEquals(7, consumption2.getShoes());
    }

    @Test
    public void testSubmitValues() {
        Account account = new Account();
        LocalDate date = LocalDate.of(2020, Month.MARCH, 1);
        account.setUsername("consumptionsubmit");
        accountRepository.save(account);

        consumptionService.submit("consumptionsubmit", date, 1, 2, 3, 4, 5, 6, 7);
        Consumption consumptionTest = consumptionRepository.findByAccountAndLocalDate(
                account, date);
        assertEquals(4, consumptionTest.getBooks());
        assertEquals(1, consumptionTest.getClothes());
        assertEquals(3, consumptionTest.getElectronics());
        assertEquals(5, consumptionTest.getFreetime());
        assertEquals(7, consumptionTest.getMiscellaneous());
        assertEquals(6, consumptionTest.getPhone());
        assertEquals(2, consumptionTest.getShoes());
    }

    @Test
    public void testCalculate() {
        Account account = new Account();
        LocalDate date = LocalDate.of(2020, Month.MARCH, 1);
        account.setUsername("consumptioncalc");
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

        assertEquals(0, consumptionService.calculateTodaysConsumptionEmission("fakeuser", date));
        assertEquals(10400, consumptionService.calculateTodaysConsumptionEmission("consumptioncalc", date));
    }

    @Test
    public void testFilledDays() {
        Account account = new Account();
        LocalDate dateNow = LocalDate.of(2020, Month.MARCH, 7);
        LocalDate startDate = LocalDate.of(2020, Month.MARCH, 1);
        account.setUsername("daytestConsumption");
        account.setStartDate(startDate);
        accountRepository.save(account);

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

        assertEquals(2, consumptionService.findFilledDays("daytestConsumption", dateNow).size());
        assertTrue(consumptionService.findFilledDays("daytestConsumption", dateNow).contains(dateNow.minusDays(1)));
        assertTrue(consumptionService.findFilledDays("daytestConsumption", dateNow).contains(dateNow.minusDays(2)));

    }

    @Test
    public void testSelectDates() {
        LocalDate date = LocalDate.of(2020, Month.MARCH, 1);
        consumptionService.setSelectedDate(date);
        assertEquals(date, consumptionService.getSelectedDate());
    }
}
