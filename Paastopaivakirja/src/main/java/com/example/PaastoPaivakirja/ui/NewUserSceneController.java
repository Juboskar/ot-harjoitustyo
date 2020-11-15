/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.PaastoPaivakirja.ui;

import com.example.PaastoPaivakirja.domain.LoginService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
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

    private JavafxApplication application;

    @Autowired
    private LoginService loginService;
    
    public void setApplication(JavafxApplication application) {
        this.application = application;
    }

}
