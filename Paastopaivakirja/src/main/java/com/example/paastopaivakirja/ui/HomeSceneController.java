package com.example.paastopaivakirja.ui;

import com.example.paastopaivakirja.domain.ConsumptionService;
import com.example.paastopaivakirja.domain.EmissionService;
import com.example.paastopaivakirja.domain.FoodService;
import com.example.paastopaivakirja.domain.LoginService;
import com.example.paastopaivakirja.domain.SummaryService;
import com.example.paastopaivakirja.domain.TrafficService;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Oskari
 */
@Component
@FxmlView("/fxml/HomeScene.fxml")
public class HomeSceneController {
    
    @Autowired
    SummaryService summaryService;
    
    @Autowired
    LoginService loginService;
    
    @Autowired
    EmissionService emissionService;
    
    @Autowired
    FoodService foodService;
    
    @Autowired
    TrafficService trafficService;
    
    @Autowired
    ConsumptionService consumptionService;
    
    @Autowired
    MainController main;
    
    @FXML
    Text name;
    
    @FXML
    Text yearlyTotal;
    
    @FXML
    Text todaysTotal;
    
    @FXML
    Text startDate;
    
    @FXML
    Text foodCheck;
    
    @FXML
    Text trafficCheck;
    
    @FXML
    Text consumptionCheck;
    
    @FXML
    Text summary;
    
    @FXML
    Text total;
    
    @FXML/**/
    Button button;
    
    @FXML
    public void logOut(ActionEvent event) {
        main.showLoginScene();
    }
    
    @FXML
    public void calculateYearlyEmissions(ActionEvent event) {
        main.showYearlyEmissionScene();
    }
    
    @FXML
    public void calculateTodaysFood(ActionEvent event) {
        main.showFoodEmissionScene();
    }
    
    @FXML
    public void calculateTodaysTraffic(ActionEvent event) {
        main.showTrafficEmissionScene();
    }
    
    @FXML
    public void calculateTodaysConsumption(ActionEvent event) {
        main.showConsumptionScene();
    }
    
    @FXML
    public void fillDays(ActionEvent event) {
        /*TODO: avaa näkymän jossa lista täyttämättömistä päivistä, 
        kun päivän valitsee, näkyy täyttämättömät kohdat 
        (ruoka/liikenne/kulutus)*/
    }
    
    @FXML
    public void initialize() {
        String user = loginService.getCurrentUser();
        LocalDate date = LocalDate.now();
        name.setText("Tervetuloa " + user);
        int sum = summaryService.calculateSummary(user);
        summary.setText("Kertyneet vuosipäästösi: " + sum / 1000 + " kg/co2");
        int yearlyEmission = emissionService.calculateYearlyEmission(user);
        int totalSum = sum + yearlyEmission;
        total.setText("Yhtensä: " + totalSum / 1000 + " kg/co2");
        yearlyTotal.setText("Kiinteät vuosipäästösi: " + yearlyEmission / 1000 + "kg/co2");
        int calculatedEmission = (foodService.calculateTodaysFoodEmission(user, date)
                + trafficService.calculateTodaysTrafficEmission(user, date)
                + consumptionService.calculateTodaysConsumptionEmission(user, date)) / 1000;
        todaysTotal.setText("Tämänpäiväiset päästösi: " + calculatedEmission + " kg/co2");
        LocalDate localStartDate = loginService.getStartDate(user);
        Long daysBetween = DAYS.between(localStartDate, date);
        startDate.setText("Päästöpäiväkirjan pitäminen aloitettu: " + localStartDate + "\nPäviä: "
                + daysBetween);
        foodCheck.setVisible(foodService.checkIfExists(user, date));
        trafficCheck.setVisible(trafficService.checkIfExists(user, date));
        consumptionCheck.setVisible(consumptionService.checkIfExists(user, date));
        
        button.setVisible(false);
    }
}
