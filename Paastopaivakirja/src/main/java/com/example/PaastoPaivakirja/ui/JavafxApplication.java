package com.example.PaastoPaivakirja.ui;

import com.example.PaastoPaivakirja.PaastoPaivakirjaApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 *
 * @author Oskari
 */
public class JavafxApplication extends Application {

    private ConfigurableApplicationContext context;
    private Scene loginScene;
    private Stage stage;
    
    @Override
    public void init() throws Exception {
        FXMLLoader loginSceneLoader = new FXMLLoader(getClass().getResource("/fxml/LoginScene.fxml"));
        Parent loginPane = loginSceneLoader.load();
        LoginSceneController loginSceneController = loginSceneLoader.getController();
        loginSceneController.setApplication(this);
        loginScene = new Scene(loginPane);
        
        ApplicationContextInitializer<GenericApplicationContext> initializer
                = (GenericApplicationContext applicationContext) -> {
                    applicationContext.registerBean(Application.class, () -> JavafxApplication.this);
                    applicationContext.registerBean(Parameters.class, () -> getParameters());
                };
        this.context = new SpringApplicationBuilder()
                .sources(PaastoPaivakirjaApplication.class)
                .initializers(initializer)
                .run(getParameters().getRaw().toArray(new String[0]));
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        setLoginScene();
        stage.show();        
    }

    @Override
    public void stop() throws Exception {
        this.context.close();
        Platform.exit();
    }
    
    public void setLoginScene() {
        stage.setScene(loginScene);
    }
}

