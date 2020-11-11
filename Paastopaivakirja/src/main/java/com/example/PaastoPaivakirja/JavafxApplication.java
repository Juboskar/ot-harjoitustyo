package com.example.PaastoPaivakirja;

import javafx.application.Application;
import javafx.application.Platform;
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

    @Override
    public void init() {
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

        //väliaikaista koodia 
        Text text = new Text("HELLO SPRING JAVAFX WORLD!!!");
        FlowPane flowPane = new FlowPane();
        flowPane.getChildren().add(text);
        Scene scene = new Scene(flowPane);
        stage.setScene(scene);
        stage.show();
        
    }

    @Override
    public void stop() throws Exception {
        this.context.close();
        Platform.exit();
    }
}

