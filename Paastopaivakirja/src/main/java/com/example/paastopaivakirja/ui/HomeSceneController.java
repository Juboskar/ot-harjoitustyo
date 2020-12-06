package com.example.paastopaivakirja.ui;

import com.example.paastopaivakirja.domain.ConsumptionService;
import com.example.paastopaivakirja.domain.EmissionService;
import com.example.paastopaivakirja.domain.FoodService;
import com.example.paastopaivakirja.domain.LoginService;
import com.example.paastopaivakirja.domain.SummaryService;
import com.example.paastopaivakirja.domain.TrafficService;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    public void initialize() {
        String user = loginService.getCurrentUser();
        LocalDate date = LocalDate.now();
        String visibleName = user;
        if (visibleName.length() > 15) {
            visibleName = user.substring(0, 15) + "...";
        }
        name.setText("Tervetuloa " + visibleName);
        int sum = summaryService.calculateSummary(user);
        summary.setText("Kertyneet vuosipäästösi: " + sum / 1000 + " kg co2 ekv");
        int yearlyEmission = emissionService.calculateYearlyEmission(user);
        int totalSum = sum + yearlyEmission;
        total.setText("Yhtensä: " + totalSum / 1000 + " kg co2 ekv");
        yearlyTotal.setText("Kiinteät vuosipäästösi: " + yearlyEmission / 1000 + " kg co2 ekv");
        int calculatedEmission = (foodService.calculateTodaysFoodEmission(user, date)
                + trafficService.calculateTodaysTrafficEmission(user, date)
                + consumptionService.calculateTodaysConsumptionEmission(user, date)) / 1000;
        todaysTotal.setText("Tämänpäiväiset päästösi: " + calculatedEmission + " kg co2 ekv");
        LocalDate localStartDate = loginService.getStartDate(user);
        Long daysBetween = DAYS.between(localStartDate, date);
        startDate.setText("Päästöpäiväkirjan pitäminen aloitettu: " + localStartDate + "\nPäiviä: "
                + daysBetween);
        foodCheck.setVisible(foodService.checkIfExists(user, date));
        trafficCheck.setVisible(trafficService.checkIfExists(user, date));
        consumptionCheck.setVisible(consumptionService.checkIfExists(user, date));

        trafficService.setSelectedDate(date);
        consumptionService.setSelectedDate(date);
        foodService.setSelectedDate(date);
    }
}
