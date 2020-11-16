/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.PaastoPaivakirja.ui;

import com.example.PaastoPaivakirja.domain.LoginService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class HomeSceneController implements Initializable {
    
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        name.setText(loginService.getCurrentUser());
    }   
}
