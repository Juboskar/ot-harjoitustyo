/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.PaastoPaivakirja.ui;

import com.example.PaastoPaivakirja.domain.LoginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Oskari
 */
@Component
@FxmlView("/fxml/NewUserScene.fxml")
public class NewUserSceneController {

    @Autowired
    private MainController main;

    @Autowired
    private LoginService loginService;

    @FXML
    TextField usernameField;

    @FXML
    private Text infoText;

    @FXML
    private void handleCreateAccount(ActionEvent event) {
        if (loginService.createAccount(usernameField.getText())) {
            infoText.setText("Käyttäjä luotu");
        } else {
            infoText.setText("Kokeile jotain muuta käyttäjätunnusta");
        }
        usernameField.clear();
    }

    @FXML
    private void returnToLogin(ActionEvent event) {
        main.showLoginScene();
    }
}
