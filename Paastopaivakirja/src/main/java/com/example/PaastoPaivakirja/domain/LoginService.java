package com.example.PaastoPaivakirja.domain;

import com.example.PaastoPaivakirja.dao.AccountRepository;
import com.example.PaastoPaivakirja.ui.MainController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Oskari
 */
@Service
public class LoginService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void login(String username, String password) {
        System.out.println("Tervetuloa " + username);   
    }
    
    public void createAccount(String username, String password){
        
    }
}
