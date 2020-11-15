package com.example.PaastoPaivakirja.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 *
 * @author Oskari
 */
public class LoginSceneController implements Initializable {

    private JavafxApplication application;
    
    public void setApplication(JavafxApplication application) {
        this.application = application;
    }
    
    @FXML
    private void HelloWorld(ActionEvent event) {
        System.out.println("Hello world!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //todo
    }
    
}
