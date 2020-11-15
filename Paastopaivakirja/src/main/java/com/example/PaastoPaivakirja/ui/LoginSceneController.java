package com.example.PaastoPaivakirja.ui;

import com.example.PaastoPaivakirja.domain.LoginService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Oskari
 */
@Component
public class LoginSceneController implements Initializable {

    private JavafxApplication application;
    
    @Autowired
    private LoginService loginService;

    public void setApplication(JavafxApplication application) {
        this.application = application;
    }

    @FXML
    private TextField usernameField;
    
    @FXML
    private TextField passwordField;
    
    @FXML
    private void handleLogin(ActionEvent event) {
        System.out.println("Hello world");
    }
    
    @FXML
    private void handleNewUser(ActionEvent event) {
        application.setNewUserScene();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //todo
    }

}
