package com.example.PaastoPaivakirja;

import com.example.PaastoPaivakirja.dao.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class PaastoPaivakirjaApplicationTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    void contextLoads() {
        
    }
}
