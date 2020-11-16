/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.PaastoPaivakirja.ui;

import com.example.PaastoPaivakirja.domain.EmissionService;
import com.example.PaastoPaivakirja.domain.LoginService;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
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
    Text sizeSliderValue;
    
    @FXML
    Text personsSliderValue;

    @FXML
    public void submit() {
        //todo, lähetetään tiedot emissionsevicelle
    }

    @FXML
    public void setSize(ObservableValue<Number> ovn, Number before, Number after) {
        sizeSliderValue.setText(after.intValue() + " m2");
    }

    @FXML
    public void setPersons(ObservableValue<Number> ovn, Number before, Number after) {
        personsSliderValue.setText(after.intValue() + " hlö");
    }
    
    @FXML
    public void returnToHome() {
        main.showHomeScene();
    }
}
