package com.example.paastopaivakirja;

import com.example.paastopaivakirja.dao.AccountRepository;
import com.example.paastopaivakirja.domain.LoginService;

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
public class LoginServiceTest {
 
    @Autowired
    AccountRepository accountRepository;
    
    @Autowired
    LoginService loginService;
    
    @Test
    public void testCreateAccount(){
        loginService.createAccount("test");
        assertEquals(accountRepository.findByUsername("test").getUsername(),"test");
    }
    
}
