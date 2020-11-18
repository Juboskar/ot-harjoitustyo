/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.PaastoPaivakirja;

import com.example.PaastoPaivakirja.dao.AccountRepository;
import com.example.PaastoPaivakirja.domain.LoginService;
import com.example.PaastoPaivakirja.model.Account;
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
