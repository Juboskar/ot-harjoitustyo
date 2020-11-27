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
    public void testDefaulValues() {
        Account account = new Account();
        LocalDate date = LocalDate.of(2020, Month.MARCH, 1);
        account.setUsername("trafficdefault");
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

        trafficService.setDefault("trafficdefault", date);

        TrafficEmission trafficTest = trafficEmissionRepository.findByAccountAndLocalDate(
                account, date);
        assertEquals(0, trafficTest.getAirplane());
        assertEquals(0, trafficTest.getCar());
        assertEquals(0, trafficTest.getLongDistanceBus());
        assertEquals(0, trafficTest.getLongDistanceTrain());
        assertEquals(0, trafficTest.getMetro());
        assertEquals(0, trafficTest.getShip());
        assertEquals(0, trafficTest.getShortDistanceBus());
        assertEquals(0, trafficTest.getShortDistanceTrain());
        assertEquals(0, trafficTest.getTram());

    }

}
