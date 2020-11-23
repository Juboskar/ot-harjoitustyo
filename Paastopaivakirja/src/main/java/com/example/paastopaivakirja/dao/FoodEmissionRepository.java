package com.example.paastopaivakirja.dao;

import com.example.paastopaivakirja.model.Account;
import com.example.paastopaivakirja.model.FoodEmission;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Oskari
 */
public interface FoodEmissionRepository extends JpaRepository<FoodEmission, Long> {

    public FoodEmission findByAccountAndDate(Account account, LocalDate date);
    
}
