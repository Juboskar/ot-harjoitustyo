/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.paastopaivakirja;

import com.example.paastopaivakirja.dao.AccountRepository;
import com.example.paastopaivakirja.dao.YearlyEmissionRepository;
import com.example.paastopaivakirja.domain.EmissionService;
import com.example.paastopaivakirja.model.Account;
import com.example.paastopaivakirja.model.YearlyEmission;
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
public class EmissionServiceTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    EmissionService emissionService;

    @Autowired
    YearlyEmissionRepository yearlyEmissionRepository;

    @Test
    public void testFindEmissionInfo() {

        Account account = new Account();
        account.setUsername("emissionTest");
        YearlyEmission yearlyEmission = new YearlyEmission();
        yearlyEmission.setElectricity(1337);
        yearlyEmission.setHouseSize(50);
        yearlyEmission.setPopulation(3);
        yearlyEmissionRepository.save(yearlyEmission);
        account.setYearlyEmission(yearlyEmission);
        accountRepository.save(account);

        assertEquals(emissionService.findEmissionInfo("emissionTest"), yearlyEmission);

    }
}
