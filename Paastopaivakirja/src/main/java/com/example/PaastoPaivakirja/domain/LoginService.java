package com.example.PaastoPaivakirja.domain;

import com.example.PaastoPaivakirja.dao.AccountRepository;
import com.example.PaastoPaivakirja.model.Account;
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
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode(password));
        // todo: tarkista mätchääkö autentikaatio

    }

    public void createAccount(String username, String password) {
        //todo: tarkista onko olemassa jo
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode(password));
        accountRepository.save(account);
    }
}
