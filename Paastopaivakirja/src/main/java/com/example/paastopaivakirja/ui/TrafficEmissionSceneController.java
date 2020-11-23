package com.example.paastopaivakirja.ui;

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
@FxmlView("/fxml/TrafficEmissionScene.fxml")
public class TrafficEmissionSceneController {

    @Autowired
    MainController main;
    
    @FXML
    Slider carSlider;

    @FXML
    Slider shortDistanceBusSlider;

    @FXML
    Slider tramSlider;

    @FXML
    Slider shortDistanceTrainSlider;

    @FXML
    Slider metroSlider;

    @FXML
    Slider longDistanceBusSlider;

    @FXML
    Slider longDistanceTrainSlider;

    @FXML
    Slider shipSlider;

    @FXML
    Slider airplainSlider;

    @FXML
    Text carSliderValue;

    @FXML
    Text shortDistanceBusSliderValue;

    @FXML
    Text tramSliderValue;

    @FXML
    Text shortDistanceTrainSliderValue;

    @FXML
    Text metroSliderValue;

    @FXML
    Text longDistanceBusSliderValue;

    @FXML
    Text longDistanceTrainSliderValue;

    @FXML
    Text shipSliderValue;

    @FXML
    Text airplaneSliderValue;

    @FXML
    public void setCar(ObservableValue<Number> ovn, Number before, Number after) {
        carSliderValue.setText(after.intValue() + " km");
    }

    @FXML
    public void setShortDistanceBus(ObservableValue<Number> ovn, Number before, Number after) {
        shortDistanceBusSliderValue.setText(after.intValue() + " km");
    }

    @FXML
    public void setTram(ObservableValue<Number> ovn, Number before, Number after) {
        tramSliderValue.setText(after.intValue() + " km");
    }

    @FXML
    public void setShortDistanceTrain(ObservableValue<Number> ovn, Number before, Number after) {
        shortDistanceTrainSliderValue.setText(after.intValue() + " km");
    }

    @FXML
    public void setMetro(ObservableValue<Number> ovn, Number before, Number after) {
        metroSliderValue.setText(after.intValue() + " km");
    }

    @FXML
    public void setLongDistanceBus(ObservableValue<Number> ovn, Number before, Number after) {
        longDistanceBusSliderValue.setText(after.intValue() + " km");
    }

    @FXML
    public void setLongDistanceTrain(ObservableValue<Number> ovn, Number before, Number after) {
        longDistanceTrainSliderValue.setText(after.intValue() + " km");
    }

    @FXML
    public void setShip(ObservableValue<Number> ovn, Number before, Number after) {
        shipSliderValue.setText(after.intValue() + " km");
    }

    @FXML
    public void setAirplane(ObservableValue<Number> ovn, Number before, Number after) {
        airplaneSliderValue.setText(after.intValue() + " km");
    }

    @FXML
    public void submit() {
        main.showHomeScene();
    }

    @FXML
    public void setDefaultValues() {

    }

}
