/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.paastopaivakirja.dao;

import com.example.paastopaivakirja.model.Account;
import com.example.paastopaivakirja.model.TrafficEmission;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Oskari
 */
@Repository
public interface TrafficEmissionRepository extends JpaRepository<TrafficEmission, Long> {

    public TrafficEmission findByAccountAndLocalDate(Account account, LocalDate date);

}
