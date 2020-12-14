package com.example.paastopaivakirja;

import com.example.paastopaivakirja.dao.AccountRepository;
import com.example.paastopaivakirja.dao.FoodEmissionRepository;
import com.example.paastopaivakirja.dao.TrafficEmissionRepository;
import com.example.paastopaivakirja.domain.FoodService;
import com.example.paastopaivakirja.domain.TrafficService;
import com.example.paastopaivakirja.model.Account;
import com.example.paastopaivakirja.model.Consumption;
import com.example.paastopaivakirja.model.FoodEmission;
import com.example.paastopaivakirja.model.TrafficEmission;
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
public class TrafficServiceTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TrafficEmissionRepository trafficEmissionRepository;

    @Autowired
    TrafficService trafficService;

    @Test
    public void testCheckIfExists() {
        Account account = new Account();
        LocalDate date = LocalDate.of(2020, Month.MARCH, 1);
        account.setUsername("traffic");
        accountRepository.save(account);
        TrafficEmission trafficEmission = new TrafficEmission();
        trafficEmission.setLocalDate(date);
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

        assertTrue(trafficService.checkIfExists("traffic", date));

        LocalDate fakeDate = LocalDate.of(2020, Month.MARCH, 2);
        assertFalse(trafficService.checkIfExists("traffic", fakeDate));
        assertFalse(trafficService.checkIfExists("fakeuser", date));
    }

    @Test
    public void testFindEmissionInfo() {
        LocalDate date = LocalDate.of(2020, Month.MARCH, 3);
        TrafficEmission trafficEmission = trafficService.findEmissionInfo("infouser", date);
        assertEquals(0, trafficEmission.getAirplane());
        assertEquals(0, trafficEmission.getCar());
        assertEquals(0, trafficEmission.getLongDistanceBus());
        assertEquals(0, trafficEmission.getLongDistanceTrain());
        assertEquals(0, trafficEmission.getMetro());
        assertEquals(0, trafficEmission.getShip());
        assertEquals(0, trafficEmission.getShortDistanceBus());
        assertEquals(0, trafficEmission.getShortDistanceTrain());
        assertEquals(0, trafficEmission.getTram());

        Account account = new Account();
        account.setUsername("trafficinfouser");
        accountRepository.save(account);
        TrafficEmission trafficEmissionTest = new TrafficEmission();
        trafficEmissionTest.setLocalDate(date);
        trafficEmissionTest.setAccount(account);
        trafficEmissionTest.setAirplane(1);
        trafficEmissionTest.setCar(2);
        trafficEmissionTest.setLongDistanceBus(3);
        trafficEmissionTest.setLongDistanceTrain(4);
        trafficEmissionTest.setMetro(5);
        trafficEmissionTest.setShip(6);
        trafficEmissionTest.setShortDistanceBus(7);
        trafficEmissionTest.setShortDistanceTrain(8);
        trafficEmissionTest.setTram(9);
        trafficEmissionRepository.save(trafficEmissionTest);

        TrafficEmission TrafficEmission2 = trafficService.findEmissionInfo("trafficinfouser", date);
        assertEquals(1, TrafficEmission2.getAirplane());
        assertEquals(2, TrafficEmission2.getCar());
        assertEquals(3, TrafficEmission2.getLongDistanceBus());
        assertEquals(4, TrafficEmission2.getLongDistanceTrain());
        assertEquals(5, TrafficEmission2.getMetro());
        assertEquals(6, TrafficEmission2.getShip());
        assertEquals(7, TrafficEmission2.getShortDistanceBus());
        assertEquals(8, TrafficEmission2.getShortDistanceTrain());
        assertEquals(9, TrafficEmission2.getTram());
    }

    @Test
    public void testSubmitValues() {
        Account account = new Account();
        LocalDate date = LocalDate.of(2020, Month.MARCH, 1);
        account.setUsername("trafficsubmit");
        accountRepository.save(account);

        trafficService.submit("trafficsubmit", date, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        TrafficEmission trafficTest = trafficEmissionRepository.findByAccountAndLocalDate(
                account, date);
        assertEquals(9, trafficTest.getAirplane());
        assertEquals(1, trafficTest.getCar());
        assertEquals(6, trafficTest.getLongDistanceBus());
        assertEquals(7, trafficTest.getLongDistanceTrain());
        assertEquals(5, trafficTest.getMetro());
        assertEquals(8, trafficTest.getShip());
        assertEquals(2, trafficTest.getShortDistanceBus());
        assertEquals(4, trafficTest.getShortDistanceTrain());
        assertEquals(3, trafficTest.getTram());
    }

    @Test
    public void testCalculate() {
        Account account = new Account();
        LocalDate date = LocalDate.of(2020, Month.MARCH, 1);
        account.setUsername("trafficcalc");
        accountRepository.save(account);

        TrafficEmission trafficEmission = new TrafficEmission();
        trafficEmission.setLocalDate(date);
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

        assertEquals(0, trafficService.calculateTodaysTrafficEmission("fakeuser", date));
        assertEquals(2355, trafficService.calculateTodaysTrafficEmission("trafficcalc", date));
    }

    @Test
    public void testFilledDays() {
        Account account = new Account();
        LocalDate dateNow = LocalDate.of(2020, Month.MARCH, 7);
        LocalDate startDate = LocalDate.of(2020, Month.MARCH, 1);
        account.setUsername("daytestTraffic");
        account.setStartDate(startDate);
        accountRepository.save(account);

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

        assertEquals(2, trafficService.findFilledDays("daytestTraffic", dateNow).size());
        assertTrue(trafficService.findFilledDays("daytestTraffic", dateNow).contains(dateNow.minusDays(1)));
        assertTrue(trafficService.findFilledDays("daytestTraffic", dateNow).contains(dateNow.minusDays(2)));

    }

    @Test
    public void testSelectDates() {
        LocalDate date = LocalDate.of(2020, Month.MARCH, 1);
        trafficService.setSelectedDate(date);

        assertEquals(date, trafficService.getSelectedDate());
    }
}
