package com.example.paastopaivakirja.dao;

import com.example.paastopaivakirja.model.Account;
import com.example.paastopaivakirja.model.FoodEmission;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Oskari
 */
@Repository
public interface FoodEmissionRepository extends JpaRepository<FoodEmission, Long> {

    public FoodEmission findByAccountAndLocalDate(Account account, LocalDate date);
    public List<FoodEmission> findByAccount(Account user);

}
