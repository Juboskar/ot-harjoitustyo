package com.example.PaastoPaivakirja.ui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Oskari
 */
@Component
public class MainController {

    @Autowired
    FxWeaver fxWeaver;

    Stage stage;

    public void start(Stage stage) {
        this.stage = stage;
        this.showLoginView();
    }

    public void showLoginView() {
        Parent root = fxWeaver.loadView(LoginSceneController.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void showNewUserView() {
        Parent root = fxWeaver.loadView(NewUserSceneController.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
