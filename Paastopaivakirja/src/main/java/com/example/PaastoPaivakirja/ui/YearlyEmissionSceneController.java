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

    ToggleGroup group;

    @FXML
    Text sliderValue;

    @FXML
    public void submit() {
        emissionService.calculateYearly(sliderValue.getText());
    }

    @FXML
    public void returnToHome() {
        main.showHomeScene();
    }

    @FXML
    public void setNumber(ObservableValue<Number> ovn, Number before, Number after) {
        sliderValue.setText(after.intValue() + " m2");
    }

}
