package com.example.paastopaivakirja.dao;

import com.example.paastopaivakirja.model.Account;
import com.example.paastopaivakirja.model.YearlyEmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Oskari
 */
@Repository
public interface YearlyEmissionRepository extends JpaRepository<YearlyEmission, Long> {
    
}
