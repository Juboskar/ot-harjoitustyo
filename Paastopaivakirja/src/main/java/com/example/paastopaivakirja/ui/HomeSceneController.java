package com.example.paastopaivakirja.ui;

import com.example.paastopaivakirja.domain.LoginService;

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
public class HomeSceneController{

    @Autowired
    LoginService loginService;

    @Autowired
    private MainController main;

    @FXML
    Text name;

    @FXML
    public void logOut(ActionEvent event) {
        main.showLoginScene();
    }

    @FXML
    public void calculateYearlyEmissions(ActionEvent event) {
        main.showYearlyEmissionScene();
    }

    @FXML
    public void initialize() {
        name.setText(loginService.getCurrentUser());
    }
}
