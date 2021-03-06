package com.example.paastopaivakirja.ui;

import com.example.paastopaivakirja.domain.EmissionService;
import com.example.paastopaivakirja.domain.House;
import com.example.paastopaivakirja.domain.LoginService;
import com.example.paastopaivakirja.model.YearlyEmission;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
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
    CheckBox ecoCheck;

    @FXML
    RadioButton apartmentRadioButton;

    @FXML
    RadioButton houseRadioButton;

    @FXML
    RadioButton rowHouseRadioButton;

    @FXML
    public void submit() {
        House house = House.APARTMENT;
        if (houseRadioButton.isSelected()) {
            house = House.HOUSE;
        }
        if (rowHouseRadioButton.isSelected()) {
            house = House.ROWHOUSE;
        }
        int electricityTypeFactor = 1;
        if (ecoCheck.isSelected()) {
            electricityTypeFactor = 0;
        }

        emissionService.submitNewValues(
                (int) houseSizeSlider.getValue(), (int) populationSlider.getValue(),
                (int) electricitySlider.getValue(), electricityTypeFactor, house,
                loginService.getCurrentUser());
        
        main.showHomeScene();
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
        YearlyEmission emission = emissionService.findEmissionInfo(user);

        int userHouseSize = emission.getHouseSize();
        houseSizeSlider.setValue(userHouseSize);
        houseSizeSliderValue.setText(userHouseSize + " m2");

        int userPopulation = emission.getPopulation();
        populationSlider.setValue(userPopulation);
        populationSliderValue.setText(userPopulation + " hlö");

        int userElectricity = emission.getElectricity();
        electricitySlider.setValue(userElectricity);
        electricitySliderValue.setText(userElectricity + " kwh");

        if (emission.getElectricityTypeFactor() == 0) {
            ecoCheck.setSelected(true);
        }

        ToggleGroup group = new ToggleGroup();
        apartmentRadioButton.setToggleGroup(group);
        houseRadioButton.setToggleGroup(group);
        rowHouseRadioButton.setToggleGroup(group);
        switch (emission.getHouse()) {
            case HOUSE:
                group.selectToggle(houseRadioButton);
                break;
            case ROWHOUSE:
                group.selectToggle(rowHouseRadioButton);
                break;
            default: //APARTMENT
                group.selectToggle(apartmentRadioButton);
                break;
        }
    }
}
