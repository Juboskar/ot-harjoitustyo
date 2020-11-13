package com.example.PaastoPaivakirja;

import com.example.PaastoPaivakirja.ui.JavafxApplication;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaastoPaivakirjaApplication {

	public static void main(String[] args) {
		Application.launch(JavafxApplication.class, args);
	}

}
