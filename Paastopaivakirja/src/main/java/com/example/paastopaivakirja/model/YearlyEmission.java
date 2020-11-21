/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.paastopaivakirja.model;

import com.example.paastopaivakirja.domain.House;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author Oskari
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YearlyEmission extends AbstractPersistable<Long> {

    private int electricity;
    private int electricityTypeFactor;
    private int houseSize;
    private int population;
    private House house;

    @OneToOne
    private Account account;

}
