/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.PaastoPaivakirja;

import com.example.PaastoPaivakirja.dao.AccountRepository;
import com.example.PaastoPaivakirja.domain.LoginService;
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
@SpringBootTest
@RunWith(SpringRunner.class)
public class LoginServiceTest {

    @Autowired
    LoginService loginService;

    //JPARepositoriot ei toimi jostain syystä testeissä. Palaan selvittämään
    @Autowired
    AccountRepository accountRepository;


}
