package com.example.paastopaivakirja;

import com.example.paastopaivakirja.dao.AccountRepository;
import com.example.paastopaivakirja.domain.LoginService;
import com.example.paastopaivakirja.model.Account;
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
public class LoginServiceTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    LoginService loginService;

    @Test
    public void testCreateAccount() {
        LocalDate date = LocalDate.of(2020, Month.MARCH, 1);
        assertTrue(loginService.createAccount("test", date));
        assertEquals(accountRepository.findByUsername("test").getUsername(), "test");
        assertFalse(loginService.createAccount("test", date));
    }

    @Test
    public void testLogin() {
        LocalDate date = LocalDate.of(2020, Month.MARCH, 1);
        loginService.createAccount("realUser", date);
        assertTrue(loginService.login("realUser"));
        assertFalse(loginService.login("notUser"));
        assertEquals("realUser", loginService.getCurrentUser());
    }
}
