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
public class Consumption extends AbstractPersistable<Long> {

    private LocalDate localDate;

    private int clothes;
    private int shoes;
    private int electronics;
    private int books;
    private int freetime;
    private int phone;
    private int miscellaneous;

    @ManyToOne
    private Account account;
}
