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

    public boolean login(String username) {
        return accountRepository.findByUsername(username) != null;
    }

    public void createAccount(String username) {
        if (accountRepository.findByUsername(username) == null) {
            Account account = new Account();
            account.setUsername(username);
            accountRepository.save(account);
        }
    }
}
