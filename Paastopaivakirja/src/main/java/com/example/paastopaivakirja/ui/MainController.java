package com.example.paastopaivakirja.ui;

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
        this.showLoginScene();
    }

    public void showLoginScene() {
        Parent root = fxWeaver.loadView(LoginSceneController.class);
        this.show(root);
    }

    public void showNewUserScene() {
        Parent root = fxWeaver.loadView(NewUserSceneController.class);
        this.show(root);
    }

    public void showHomeScene() {
        Parent root = fxWeaver.loadView(HomeSceneController.class);
        this.show(root);
    }

    public void showYearlyEmissionScene() {
        Parent root = fxWeaver.loadView(YearlyEmissionSceneController.class);
        this.show(root);
    }

    private void show(Parent root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
