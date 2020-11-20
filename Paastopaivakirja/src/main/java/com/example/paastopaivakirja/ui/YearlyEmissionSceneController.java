package com.example.paastopaivakirja.ui;

import com.example.paastopaivakirja.domain.EmissionService;
import com.example.paastopaivakirja.domain.LoginService;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Oskari
 */
@Component
@FxmlView("/fxml/YearlyEmissionScene.fxml")
public class YearlyEmissionSceneController {

    @Autowired
    private MainController main;

    @Autowired
    private LoginService loginService;

    @Autowired
    private EmissionService emissionService;

    @FXML
    Slider houseSizeSlider;

    @FXML
    Slider populationSlider;

    @FXML
    Slider electricitySlider;

    @FXML
    Text houseSizeSliderValue;

    @FXML
    Text populationSliderValue;

    @FXML
    Text electricitySliderValue;

    @FXML
    public void submit() {
        emissionService.submitNewValues((int) houseSizeSlider.getValue(),
                (int) populationSlider.getValue(),
                (int) electricitySlider.getValue(),
                loginService.getCurrentUser());
    }

    @FXML
    public void setHouseSize(ObservableValue<Number> ovn, Number before, Number after) {
        houseSizeSliderValue.setText(after.intValue() + " m2");
    }

    @FXML
    public void setPopulation(ObservableValue<Number> ovn, Number before, Number after) {
        populationSliderValue.setText(after.intValue() + " hlö");
    }

    @FXML
    public void setElectricity(ObservableValue<Number> ovn, Number before, Number after) {
        electricitySliderValue.setText(after.intValue() + " kWh");
    }

    @FXML
    public void returnToHome() {
        main.showHomeScene();
    }

    @FXML
    public void initialize() {
        String user = loginService.getCurrentUser();
        int userHouseSize = emissionService.findUserHouseSize(user);
        houseSizeSlider.setValue(userHouseSize);
        houseSizeSliderValue.setText(userHouseSize + " m2");
        int userPopulation = emissionService.findUserPopulation(user);
        populationSlider.setValue(userPopulation);
        populationSliderValue.setText(userPopulation + " hlö");
        int userElectricity = emissionService.findUserElectricity(user);
        electricitySlider.setValue(userElectricity);
        electricitySliderValue.setText(userElectricity + " kwh");
    }
}
