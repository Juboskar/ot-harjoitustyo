package com.example.PaastoPaivakirja.domain;

import com.example.PaastoPaivakirja.dao.AccountRepository;
import com.example.PaastoPaivakirja.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

/**
 *
 * @author Oskari
 */
@Service
public class LoginService {

    @Autowired
    AccountRepository accountRepository;

    public void login(String username) {
        if (accountRepository.findByUsername(username) != null) {
            System.out.println("Olet kirjautuneena");
        } else {
            System.out.println("Käyttäjää ei löydy");
        }
    }

    public void createAccount(String username) {

        if (accountRepository.findByUsername(username) == null) {
            Account account = new Account();
            account.setUsername(username);
            accountRepository.save(account);
            System.out.println("Käyttäjä luotu");
        } else {
            System.out.println("On jo olemassa");
        }
    }
}
