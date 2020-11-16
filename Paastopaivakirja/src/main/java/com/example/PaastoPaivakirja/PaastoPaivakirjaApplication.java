package com.example.PaastoPaivakirja;

import com.example.PaastoPaivakirja.ui.JavafxApplication;
import com.example.PaastoPaivakirja.ui.MainController;
import java.util.TimeZone;
import javafx.application.Application;
import javax.annotation.PostConstruct;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.spring.SpringFxWeaver;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Oskari
 */
@SpringBootApplication
public class PaastoPaivakirjaApplication {

    @Bean
    public FxWeaver fxWeaver(ConfigurableApplicationContext applicationContext) {
        return new SpringFxWeaver(applicationContext);
    }

    @PostConstruct
    public void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+2"));
    }

    public static void main(String[] args) {
        Application.launch(JavafxApplication.class, args);
    }

}
