package com.example.paastopaivakirja.domain;

import com.example.paastopaivakirja.dao.AccountRepository;
import com.example.paastopaivakirja.model.YearlyEmission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.paastopaivakirja.dao.YearlyEmissionRepository;

/**
 * Calculates and handles users yearly stationary emission info
 *
 * @author Oskari
 */
@Service
public class EmissionService {

    @Autowired
    YearlyEmissionRepository yearlyEmissionRepository;

    @Autowired
    AccountRepository accountRepository;

    public YearlyEmission findEmissionInfo(String username) {
        return accountRepository.findByUsername(username).getYearlyEmission();
    }

    /**
     * Overwrites and saves matching YearlyEmission with given values
     *
     * @param username username of user
     * @param houseSize size of users house
     * @param population number of people living in the same household as user
     * @param electricityTypeFactor electricity type 0 if eco electricity,
     * default 1
     * @param house type of users house
     * @param electricity total consumed electricity of the year (kwh)
     *
     */
    public void submitNewValues(int houseSize, int population, int electricity,
            int electricityTypeFactor, House house, String username) {
        YearlyEmission emission = accountRepository.findByUsername(username).getYearlyEmission();
        emission.setHouseSize(houseSize);
        emission.setPopulation(population);
        emission.setElectricity(electricity);
        emission.setElectricityTypeFactor(electricityTypeFactor);
        emission.setHouse(house);
        yearlyEmissionRepository.save(emission);
    }

    /**
     * Calculates users yearly stationary emission summary: Building emissions:
     * users house size * users house type; Electicity emissions = users
     * electricity * 281 co2/kwh * electicity type (eco electricity is
     * practically emission free); House warming emission = house size * 241 kwh
     * * 267 co2 / kwh; Divided with population of household; +400000g co2 eq
     * food emissions (other than daily calulated)
     *
     * @param username username of user
     * @return calculated total summary of emission g co2 eq
     */
    public int calculateYearlyEmission(String username) {
        YearlyEmission emission = accountRepository.findByUsername(username).getYearlyEmission();
        int calculatedBuildingEmission = emission.getHouseSize()
                * emission.getHouse().getEmission();
        int calculatedElectricityEmission
                = emission.getElectricity() * 281 * emission.getElectricityTypeFactor();
        int warmingEmission = emission.getHouseSize() * 241 * 267;
        int total = (calculatedBuildingEmission + calculatedElectricityEmission + warmingEmission)
                / emission.getPopulation();
        total += 400000;
        return total;
    }
}
