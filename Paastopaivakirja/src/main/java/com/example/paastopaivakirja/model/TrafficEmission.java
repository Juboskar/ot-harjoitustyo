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
public class TrafficEmission extends AbstractPersistable<Long> {

    private LocalDate localDate;

    private int car;
    private int shortDistanceBus;
    private int tram;
    private int shortDistanceTrain;
    private int metro;
    private int longDistanceBus;
    private int longDistanceTrain;
    private int ship;
    private int airplane;

    @ManyToOne
    private Account account;
}
