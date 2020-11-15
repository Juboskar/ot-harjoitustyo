package com.example.PaastoPaivakirja.ui;

import com.example.PaastoPaivakirja.PaastoPaivakirjaApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * @author Oskari
 */
public class JavafxApplication extends Application {
    
    private FxWeaver fxWeaver;
    private MainController mainController;
    private ConfigurableApplicationContext context;
    
    @Override
    public void init() throws Exception {
        String[] args = getParameters().getRaw().toArray(new String[0]);
        this.context = new SpringApplicationBuilder()
                .sources(PaastoPaivakirjaApplication.class)
                .run(args);
        fxWeaver = context.getBean(FxWeaver.class);
        mainController = context.getBean(MainController.class);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        mainController.start(stage, fxWeaver);
    }
    
    @Override
    public void stop() throws Exception {
        this.context.close();
        Platform.exit();
    }
    
}
