package com.example.paastopaivakirja.ui;

import com.example.paastopaivakirja.domain.EmissionService;
import com.example.paastopaivakirja.domain.FoodService;
import com.example.paastopaivakirja.domain.LoginService;
import java.time.LocalDate;

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
    LoginService loginService;

    @Autowired
    EmissionService emissionService;

    @Autowired
    FoodService foodService;

    @Autowired
    private MainController main;

    @FXML
    Text name;

    @FXML
    Text yearlyTotal;

    @FXML
    Text todaysTotal;

    @FXML
    Text startDate;

    @FXML
    public void logOut(ActionEvent event) {
        main.showLoginScene();
    }

    @FXML
    public void calculateYearlyEmissions(ActionEvent event) {
        main.showYearlyEmissionScene();
    }

    @FXML
    public void calculateToday(ActionEvent event) {
        main.showFoodEmissionScene();
    }

    @FXML
    public void initialize() {
        String user = loginService.getCurrentUser();
        name.setText("Tervetuloa " + user);
        yearlyTotal.setText("Kiinteät vuosipäästösi: " + emissionService.calculateYearlyEmission(user) + "kg co2");
        todaysTotal.setText("Tämänpäiväiset päästösi: " + foodService.calculateTodaysFoodEmission(user, LocalDate.now()) +" g/co2" );
        startDate.setText("Vuosipäästöjen laskeminen aloitettu: " + loginService.getStartDate());
    }
}
