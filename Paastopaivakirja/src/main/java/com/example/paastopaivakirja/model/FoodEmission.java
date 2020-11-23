package com.example.paastopaivakirja.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
public class FoodEmission extends AbstractPersistable<Long> {

    private LocalDate date;

    private int cow;
    private int pig;
    private int fish;
    private int cheese;
    private int rice;
    private int egg;
    private int milk;
    private int vegetable;
            
    private int restaurant;

    @ManyToOne
    Account account;

}
