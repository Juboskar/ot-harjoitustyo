/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.paastopaivakirja.ui;

import com.example.paastopaivakirja.domain.ConsumptionService;
import com.example.paastopaivakirja.domain.LoginService;
import com.example.paastopaivakirja.model.Consumption;
import java.time.LocalDate;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Oskari
 */
@Component
@FxmlView("/fxml/ConsumptionScene.fxml")
public class ConsumptionSceneController {

    @Autowired
    LoginService loginService;

    @Autowired
    ConsumptionService consumptionService;

    @Autowired
    MainController main;

    @FXML
    Slider clothesSlider;

    @FXML
    Slider shoesSlider;

    @FXML
    Slider electronicsSlider;

    @FXML
    Slider booksSlider;

    @FXML
    Slider freetimeSlider;

    @FXML
    Slider phoneSlider;

    @FXML
    Slider miscellaneousSlider;

    @FXML
    Text clothesSliderValue;

    @FXML
    Text shoesSliderValue;

    @FXML
    Text electronicsSliderValue;

    @FXML
    Text booksSliderValue;

    @FXML
    Text freetimeSliderValue;

    @FXML
    Text phoneSliderValue;

    @FXML
    Text miscellaneousSliderValue;

    @FXML
    public void setClothes(ObservableValue<Number> ovn, Number before, Number after) {
        clothesSliderValue.setText(after.intValue() + " €");
    }

    @FXML
    public void setShoes(ObservableValue<Number> ovn, Number before, Number after) {
        shoesSliderValue.setText(after.intValue() + " €");
    }

    @FXML
    public void setElectronics(ObservableValue<Number> ovn, Number before, Number after) {
        electronicsSliderValue.setText(after.intValue() + " €");
    }

    @FXML
    public void setBooks(ObservableValue<Number> ovn, Number before, Number after) {
        booksSliderValue.setText(after.intValue() + " €");
    }

    @FXML
    public void setFreetime(ObservableValue<Number> ovn, Number before, Number after) {
        freetimeSliderValue.setText(after.intValue() + " €");
    }

    @FXML
    public void setPhone(ObservableValue<Number> ovn, Number before, Number after) {
        phoneSliderValue.setText(after.intValue() + " €");
    }

    @FXML
    public void setMiscellaneous(ObservableValue<Number> ovn, Number before, Number after) {
        miscellaneousSliderValue.setText(after.intValue() + " €");
    }

    @FXML
    public void setDefault() {
    consumptionService.setDefault(loginService.getCurrentUser(), LocalDate.now());
    main.showConsumptionScene();
    }

    @FXML
    public void submit() {

        consumptionService.submit(loginService.getCurrentUser(), LocalDate.now(),
                (int) clothesSlider.getValue(),
                (int) shoesSlider.getValue(),
                (int) electronicsSlider.getValue(),
                (int) booksSlider.getValue(),
                (int) freetimeSlider.getValue(),
                (int) phoneSlider.getValue(),
                (int) miscellaneousSlider.getValue());
        main.showHomeScene();
    }

    @FXML
    public void initialize() {
        String user = loginService.getCurrentUser();
        Consumption consumption = consumptionService.findEmissionInfo(user, LocalDate.now());

        int clothes = consumption.getClothes();
        clothesSlider.setValue(clothes);
        clothesSliderValue.setText(clothes + " €");

        int shoes = consumption.getShoes();
        shoesSlider.setValue(shoes);
        shoesSliderValue.setText(shoes + " €");

        int electronics = consumption.getElectronics();
        electronicsSlider.setValue(electronics);
        electronicsSliderValue.setText(electronics + " €");

        int books = consumption.getBooks();
        booksSlider.setValue(books);
        booksSliderValue.setText(books + " €");

        int freetime = consumption.getFreetime();
        freetimeSlider.setValue(freetime);
        freetimeSliderValue.setText(freetime + " €");

        int phone = consumption.getPhone();
        phoneSlider.setValue(phone);
        phoneSliderValue.setText(phone + " €");

        int miscellaneous = consumption.getMiscellaneous();
        miscellaneousSlider.setValue(miscellaneous);
        miscellaneousSliderValue.setText(miscellaneous + " €");
    }

}
