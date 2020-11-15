package com.example.PaastoPaivakirja.ui;

import com.example.PaastoPaivakirja.PaastoPaivakirjaApplication;
import com.example.PaastoPaivakirja.domain.LoginService;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
    private Scene newUserScene;
    private Stage stage;

    @Override
    public void init() throws Exception {
        String[] args = getParameters().getRaw().toArray(new String[0]);
        this.context = new SpringApplicationBuilder()
                .sources(PaastoPaivakirjaApplication.class)
                .run(args);
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
        FxWeaver fxWeaverLogin = context.getBean(FxWeaver.class);
        Parent loginPane = fxWeaverLogin.loadView(LoginSceneController.class);
        loginScene = new Scene(loginPane);
        stage.setScene(loginScene);
    }

    public void setNewUserScene() {
        FxWeaver fxWeaverNewUser = context.getBean(FxWeaver.class);
        Parent newUserPane = fxWeaverNewUser.loadView(NewUserSceneController.class);
        loginScene = new Scene(newUserPane);
        stage.setScene(newUserScene);
    }
}
