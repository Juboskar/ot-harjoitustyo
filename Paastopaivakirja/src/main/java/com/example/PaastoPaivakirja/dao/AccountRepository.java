package com.example.PaastoPaivakirja.dao;

import org.springframework.data.jpa.repository.JpaRepository;


/**
 *
 * @author Oskari
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
    
}
