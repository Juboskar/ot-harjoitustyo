/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public TrafficEmission findEmissionInfo(String username, LocalDate date) {
        Account user = accountRepository.findByUsername(username);
        TrafficEmission trafficEmission = trafficEmissionRepository.findByAccountAndLocalDate(user, date);
        if (trafficEmission == null) {
            trafficEmission = new TrafficEmission();
            trafficEmission.setAccount(user);
            trafficEmission.setLocalDate(date);
            setDefaultValues(trafficEmission);
        }
        return trafficEmissionRepository.findByAccountAndLocalDate(user, date);
    }

    public void setDefault(String username, LocalDate date) {
        Account user = accountRepository.findByUsername(username);
        TrafficEmission trafficEmission = trafficEmissionRepository.findByAccountAndLocalDate(user, date);
        setDefaultValues(trafficEmission);
    }

    private void setDefaultValues(TrafficEmission trafficEmission) {
        trafficEmission.setAirplane(0);
        trafficEmission.setCar(0);
        trafficEmission.setLongDistanceBus(0);
        trafficEmission.setLongDistanceTrain(0);
        trafficEmission.setMetro(0);
        trafficEmission.setShip(0);
        trafficEmission.setShortDistanceBus(0);
        trafficEmission.setShortDistanceTrain(0);
        trafficEmission.setTram(0);
        trafficEmissionRepository.save(trafficEmission);

    }

    public void submit(String username, LocalDate date, int car, int shortDistanceBus,
            int tram, int shortDistanceTrain, int metro, int longDistanceBus,
            int longDistanceTrain, int ship, int airplane) {
        Account user = accountRepository.findByUsername(username);
        TrafficEmission trafficEmission = trafficEmissionRepository.findByAccountAndLocalDate(user, date);
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

    public int calculateTodaysTrafficEmission(String username, LocalDate date) {
        Account user = accountRepository.findByUsername(username);
        TrafficEmission trafficEmission = trafficEmissionRepository.findByAccountAndLocalDate(user, date);
        if (trafficEmission == null) {
            return 0;
        }
        /*kerrotaan kunkin liikennemuodon kilometrimäärät keskimääräisillä päästmäärillä.
        Lentokoneen ja laivan päästöt WWF:n laskurista.*/
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

    public void setSelectedDate(LocalDate date) {
        this.selectedDate = date;
    }

    public LocalDate getSelectedDate() {
        return selectedDate;
    }
}
