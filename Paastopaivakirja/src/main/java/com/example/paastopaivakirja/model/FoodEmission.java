package com.example.paastopaivakirja.model;

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
    
    @ManyToOne
    Account account;
    
}
