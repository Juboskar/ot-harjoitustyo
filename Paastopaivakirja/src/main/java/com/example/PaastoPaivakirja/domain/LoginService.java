package com.example.paastopaivakirja.domain;

import com.example.paastopaivakirja.dao.AccountRepository;
import com.example.paastopaivakirja.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Oskari
 */
@Service
public class LoginService {

    @Autowired
    AccountRepository accountRepository;

    String currentUser;

    public boolean login(String username) {
        if (accountRepository.findByUsername(username) != null) {
            currentUser = username;
            return true;
        }
        return false;
    }

    public boolean createAccount(String username) {
        if (accountRepository.findByUsername(username) == null) {
            Account account = new Account();
            account.setUsername(username);
            accountRepository.save(account);
            return true;
        }
        return false;
    }

    public String getCurrentUser() {
        return currentUser;
    }
}
