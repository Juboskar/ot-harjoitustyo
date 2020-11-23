package com.example.paastopaivakirja.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
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
public class Account extends AbstractPersistable<Long> {

    private String username;

    private LocalDate startDate;

    @OneToOne(fetch = FetchType.EAGER)
    private YearlyEmission yearlyEmission;

    @OneToMany(mappedBy = "account")
    private List<FoodEmission> foodEmission = new ArrayList<>();

    @OneToMany(mappedBy = "account")
    private List<TrafficEmission> trafficEmission = new ArrayList<>();

    @OneToMany(mappedBy = "account")
    private List<Consumption> consumption = new ArrayList<>();
}
