package com.example.paastopaivakirja.domain;

import com.example.paastopaivakirja.dao.AccountRepository;
import com.example.paastopaivakirja.dao.TrafficEmissionRepository;
import com.example.paastopaivakirja.model.Account;
import com.example.paastopaivakirja.model.TrafficEmission;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Calculates and handles users traffic emission info
 *
 * @author Oskari
 */
@Service
public class TrafficService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TrafficEmissionRepository trafficEmissionRepository;

    private LocalDate selectedDate;

    /**
     * Checks if user has already given dates traffic emission info
     *
     * @param username username of user
     * @param date date of traffic emission info
     * @return true if traffic emission info already exists, false if not
     */
    public boolean checkIfExists(String username, LocalDate date) {
        Account user = accountRepository.findByUsername(username);
        TrafficEmission trafficEmission = trafficEmissionRepository.findByAccountAndLocalDate(user, date);
        return trafficEmission != null;
    }

    /**
     * Checks if user has already given dates traffic emission info, if not
     * returns TrafficEmission with default values
     *
     * @param username username of user
     * @param date date of traffic emission info
     * @return TrafficEmission object by user and date
     */
    public TrafficEmission findEmissionInfo(String username, LocalDate date) {
        Account user = accountRepository.findByUsername(username);
        TrafficEmission trafficEmission = trafficEmissionRepository.findByAccountAndLocalDate(user, date);
        if (trafficEmission == null) {
            trafficEmission = new TrafficEmission();
            trafficEmission.setAccount(user);
            trafficEmission.setLocalDate(date);
            return setDefaultValues(trafficEmission);
        }
        return trafficEmissionRepository.findByAccountAndLocalDate(user, date);
    }

    private TrafficEmission setDefaultValues(TrafficEmission trafficEmission) {
        trafficEmission.setAirplane(0);
        trafficEmission.setCar(0);
        trafficEmission.setLongDistanceBus(0);
        trafficEmission.setLongDistanceTrain(0);
        trafficEmission.setMetro(0);
        trafficEmission.setShip(0);
        trafficEmission.setShortDistanceBus(0);
        trafficEmission.setShortDistanceTrain(0);
        trafficEmission.setTram(0);
        return trafficEmission;

    }

    /**
     * Overwrites and saves matching TrafficEmission with given values
     *
     * @param username username of user
     * @param date date of traffic emission info
     * @param car kilometres driven by car of selected date
     * @param shortDistanceBus kilometres driven by short distance bus of
     * selected date
     * @param tram kilometres driven by tram of selected date
     * @param shortDistanceTrain kilometres driven by short distance train of
     * selected date
     * @param metro kilometres driven by metro of selected date
     * @param longDistanceBus kilometres driven by long distance bus of selected
     * date
     * @param longDistanceTrain kilometres driven by long distance train of
     * selected date
     * @param ship kilometres travelled by ship of selected date
     * @param airplane kilometres travelled by airplane of selected date
     */
    public void submit(String username, LocalDate date, int car, int shortDistanceBus,
            int tram, int shortDistanceTrain, int metro, int longDistanceBus,
            int longDistanceTrain, int ship, int airplane) {
        Account user = accountRepository.findByUsername(username);
        TrafficEmission trafficEmission = trafficEmissionRepository.findByAccountAndLocalDate(user, date);
        if (trafficEmission == null) {
            trafficEmission = new TrafficEmission();
            trafficEmission.setAccount(user);
            trafficEmission.setLocalDate(date);
        }
        trafficEmission.setAirplane(airplane);
        trafficEmission.setCar(car);
        trafficEmission.setLongDistanceBus(longDistanceBus);
        trafficEmission.setLongDistanceTrain(longDistanceTrain);
        trafficEmission.setMetro(metro);
        trafficEmission.setShip(ship);
        trafficEmission.setShortDistanceBus(shortDistanceBus);
        trafficEmission.setShortDistanceTrain(shortDistanceTrain);
        trafficEmission.setTram(tram);
        trafficEmissionRepository.save(trafficEmission);
    }

    /**
     * Calculates selected users emission caused by traffic of selected day;
     * Kilometres * co2/km per transport
     *
     * @param username username of user
     * @param date date of traffic emission info
     * @return calculated int value (g co2 ekv)
     */
    public int calculateTodaysTrafficEmission(String username, LocalDate date) {
        Account user = accountRepository.findByUsername(username);
        TrafficEmission trafficEmission = trafficEmissionRepository.findByAccountAndLocalDate(user, date);
        if (trafficEmission == null) {
            return 0;
        }
        int calculated = 0;
        calculated += trafficEmission.getAirplane() * 289;
        calculated += trafficEmission.getCar() * 200;
        calculated += trafficEmission.getLongDistanceBus() * 54;
        calculated += trafficEmission.getLongDistanceTrain() * 1;
        calculated += trafficEmission.getMetro() * 1;
        calculated += trafficEmission.getShip() * 167.00;
        calculated += trafficEmission.getShortDistanceBus() * 68;
        calculated += trafficEmission.getShortDistanceTrain() * 1;
        calculated += trafficEmission.getTram() * 1;
        return calculated;
    }

    /**
     * Finds a list of days of users saved emission caused by traffic
     *
     * @param username username of user
     * @param dateNow current date
     * @return list of already saved TrafficEmission dates
     */
    public List<LocalDate> findFilledDays(String username, LocalDate dateNow) {
        Account user = accountRepository.findByUsername(username);
        LocalDate startDate = user.getStartDate();
        List<LocalDate> filled = new ArrayList<>();
        long daysBetween = DAYS.between(startDate, dateNow);
        for (int i = 0; i < daysBetween; i++) {
            LocalDate date = startDate.plusDays(i);
            if (trafficEmissionRepository.findByAccountAndLocalDate(user, date) != null) {
                filled.add(date);
            }
        }
        return filled;
    }

    /**
     * Selects a date
     *
     * @param date date that should be selected
     */
    public void setSelectedDate(LocalDate date) {
        this.selectedDate = date;
    }

    /**
     * @return current selected day
     */
    public LocalDate getSelectedDate() {
        return selectedDate;
    }
}
