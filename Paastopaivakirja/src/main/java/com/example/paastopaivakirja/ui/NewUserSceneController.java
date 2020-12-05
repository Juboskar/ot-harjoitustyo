package com.example.paastopaivakirja.ui;

import com.example.paastopaivakirja.domain.LoginService;
import java.time.LocalDate;

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
        if (usernameField.getText().length() > 255) {
            infoText.setText("Käyttäjätunnus liian pitkä");
        } else if (loginService.createAccount(usernameField.getText(), LocalDate.now())) {
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
