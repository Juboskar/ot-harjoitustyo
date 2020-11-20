package com.example.paastopaivakirja.ui;

import com.example.paastopaivakirja.PaastopaivakirjaApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * @author Oskari
 */
public class JavafxApplication extends Application {
    
    private MainController mainController;
    private ConfigurableApplicationContext context;
    
    @Override
    public void init() throws Exception {
        String[] args = getParameters().getRaw().toArray(new String[0]);
        this.context = new SpringApplicationBuilder()
                .sources(PaastopaivakirjaApplication.class)
                .run(args);
        mainController = context.getBean(MainController.class);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        mainController.start(stage);
    }
    
    @Override
    public void stop() throws Exception {
        this.context.close();
        Platform.exit();
    }
    
}
