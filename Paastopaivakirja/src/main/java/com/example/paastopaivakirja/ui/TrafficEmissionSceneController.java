package com.example.paastopaivakirja.ui;

import com.example.paastopaivakirja.domain.LoginService;
import com.example.paastopaivakirja.domain.TrafficService;
import com.example.paastopaivakirja.model.TrafficEmission;
import java.time.LocalDate;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;
import javafx.util.Callback;
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

    @Autowired
    LoginService loginService;

    @Autowired
    TrafficService trafficService;

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
    Slider airplaneSlider;

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
    DatePicker date;

    @FXML
    Text dateText;

    @FXML
    public void selectDate() {
        trafficService.setSelectedDate(date.getValue());
        main.showTrafficEmissionScene();
    }

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
        trafficService.submit(loginService.getCurrentUser(), trafficService.getSelectedDate(),
                (int) carSlider.getValue(),
                (int) shortDistanceBusSlider.getValue(),
                (int) tramSlider.getValue(),
                (int) shortDistanceTrainSlider.getValue(),
                (int) metroSlider.getValue(),
                (int) longDistanceBusSlider.getValue(),
                (int) longDistanceTrainSlider.getValue(),
                (int) shipSlider.getValue(),
                (int) airplaneSlider.getValue());
        main.showHomeScene();
    }

    @FXML
    public void setDefaultValues() {
        main.showHomeScene();
    }

    @FXML
    public void initialize() {
        String user = loginService.getCurrentUser();

        List<LocalDate> filledDays = trafficService.findFilledDays(user, LocalDate.now());

        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isBefore(loginService.getStartDate(user))) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        } else if (item.isAfter(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                        if (filledDays.contains(item)) {
                            this.setStyle("-fx-background-color: #99FF99");
                        }
                    }
                };
            }
        };
        date.setDayCellFactory(dayCellFactory);
        date.setEditable(false);

        LocalDate selectedDate = trafficService.getSelectedDate();
        date.setValue(selectedDate);
        dateText.setText("Täydennä " + selectedDate + " matkustamasi matkat");

        TrafficEmission emission = trafficService.findEmissionInfo(user, selectedDate);

        int car = emission.getCar();
        carSlider.setValue(car);
        carSliderValue.setText(car + " km");

        int shortDistanceBus = emission.getShortDistanceBus();
        shortDistanceBusSlider.setValue(shortDistanceBus);
        shortDistanceBusSliderValue.setText(shortDistanceBus + " km");

        int tram = emission.getTram();
        tramSlider.setValue(tram);
        tramSliderValue.setText(tram + " km");

        int shortDistanceTrain = emission.getShortDistanceTrain();
        shortDistanceTrainSlider.setValue(shortDistanceTrain);
        shortDistanceTrainSliderValue.setText(shortDistanceTrain + " km");

        int metro = emission.getMetro();
        metroSlider.setValue(metro);
        metroSliderValue.setText(metro + " km");

        int longDistanceBus = emission.getLongDistanceBus();
        longDistanceBusSlider.setValue(longDistanceBus);
        longDistanceBusSliderValue.setText(longDistanceBus + " km");

        int longDistanceTrain = emission.getLongDistanceTrain();
        longDistanceTrainSlider.setValue(longDistanceTrain);
        longDistanceTrainSliderValue.setText(longDistanceTrain + " km");

        int ship = emission.getShip();
        shipSlider.setValue(ship);
        shipSliderValue.setText(ship + " km");

        int airplane = emission.getAirplane();
        airplaneSlider.setValue(airplane);
        airplaneSliderValue.setText(airplane + " km");
    }
}
