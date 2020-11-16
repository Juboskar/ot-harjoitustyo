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
@FxmlView("/fxml/LoginScene.fxml")
public class LoginSceneController {

    @Autowired
    private MainController main;

    @Autowired
    private LoginService loginService;

    @FXML
    private TextField usernameField;

    @FXML
    private Text infoField;

    @FXML
    private void handleLogin(ActionEvent event) {
        if (loginService.login(usernameField.getText())) {
            main.showHomeScene();
        } else {
            infoField.setText("Käyttäjää ei löydy");
        }
    }

    @FXML
    private void handleNewUser(ActionEvent event) {
        main.showNewUserScene();
    }

}
