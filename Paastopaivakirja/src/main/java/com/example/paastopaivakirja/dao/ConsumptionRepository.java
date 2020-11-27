package com.example.paastopaivakirja.dao;

import com.example.paastopaivakirja.model.Account;
import com.example.paastopaivakirja.model.Consumption;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Oskari
 */
@Repository
public interface ConsumptionRepository extends JpaRepository<Consumption, Long> {

    public Consumption findByAccountAndLocalDate(Account account, LocalDate date);
    public List<Consumption> findByAccount(Account user);

}
