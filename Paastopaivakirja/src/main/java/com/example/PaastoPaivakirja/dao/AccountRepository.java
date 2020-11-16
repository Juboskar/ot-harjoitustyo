package com.example.PaastoPaivakirja.dao;

import com.example.PaastoPaivakirja.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 *
 * @author Oskari
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    public Account findByUsername(String username);
    
}
