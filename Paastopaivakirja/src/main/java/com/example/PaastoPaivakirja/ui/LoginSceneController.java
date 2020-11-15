package com.example.PaastoPaivakirja.ui;

import com.example.PaastoPaivakirja.domain.LoginService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private void Login(ActionEvent event) {
        System.out.println("Hello world!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //todo
    }

}
