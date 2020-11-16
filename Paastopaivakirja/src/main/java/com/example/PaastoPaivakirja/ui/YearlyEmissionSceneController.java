/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.PaastoPaivakirja.ui;

import com.example.PaastoPaivakirja.domain.EmissionService;
import com.example.PaastoPaivakirja.domain.LoginService;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
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
    RadioButton radioButton1;

    @FXML
    RadioButton radioButton2;

    @FXML
    RadioButton radioButton3;

    @FXML
    public void submit() {
        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
        String toggleGroupValue = selectedRadioButton.getText();
        emissionService.calculateYearly(toggleGroupValue);
    }

    @FXML
    public void initialize() {
        group = new ToggleGroup();
        radioButton1.setToggleGroup(group);
        radioButton2.setToggleGroup(group);
        radioButton3.setToggleGroup(group);
    }

}
