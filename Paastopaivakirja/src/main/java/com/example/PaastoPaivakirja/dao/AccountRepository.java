package com.example.paastopaivakirja.dao;

import com.example.paastopaivakirja.model.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * @author Oskari
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    public Account findByUsername(String username);
    
}
