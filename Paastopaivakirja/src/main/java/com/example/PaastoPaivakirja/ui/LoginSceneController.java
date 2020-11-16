package com.example.PaastoPaivakirja.ui;

import com.example.PaastoPaivakirja.domain.LoginService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 *
 * @author Oskari
 */
@Component
@FxmlView("/fxml/LoginScene.fxml")
public class LoginSceneController  {
    
    @Autowired
    private MainController main;
    
    @Autowired
    private LoginService loginService;

    @FXML
    private TextField usernameField;
    
    
    @FXML
    private void handleLogin(ActionEvent event) {  
        loginService.login(usernameField.getText());
    }
    
    @FXML
    private void handleNewUser(ActionEvent event) {
       main.showNewUserView();
    }


}
