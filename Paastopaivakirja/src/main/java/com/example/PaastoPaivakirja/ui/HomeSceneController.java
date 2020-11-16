/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.PaastoPaivakirja.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Oskari
 */
@Component
@FxmlView("/fxml/HomeScene.fxml")
public class HomeSceneController {

    @Autowired
    private MainController main;

    @FXML
    public void logOut(ActionEvent event) {
        main.showLoginScene();
    }

}
