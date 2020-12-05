package com.example.paastopaivakirja.ui;

import com.example.paastopaivakirja.domain.FoodService;
import com.example.paastopaivakirja.domain.LoginService;
import com.example.paastopaivakirja.model.FoodEmission;
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
@FxmlView("/fxml/FoodEmissionScene.fxml")
public class FoodEmissionSceneController {

    @Autowired
    MainController main;

    @Autowired
    FoodService foodService;

    @Autowired
    LoginService loginService;

    @FXML
    Slider cowSlider;

    @FXML
    Slider pigSlider;

    @FXML
    Slider fishSlider;

    @FXML
    Slider cheeseSlider;

    @FXML
    Slider riceSlider;

    @FXML
    Slider eggSlider;

    @FXML
    Slider restaurantSlider;

    @FXML
    Slider milkSlider;

    @FXML
    Slider vegetableSlider;

    @FXML
    Text cowSliderValue;

    @FXML
    Text pigSliderValue;

    @FXML
    Text fishSliderValue;

    @FXML
    Text cheeseSliderValue;

    @FXML
    Text riceSliderValue;

    @FXML
    Text eggSliderValue;

    @FXML
    Text restaurantSliderValue;

    @FXML
    Text milkSliderValue;

    @FXML
    Text vegetableSliderValue;

    @FXML
    DatePicker date;

    @FXML
    Text dateText;

    @FXML
    Text infoText;

    @FXML
    public void selectDate() {
        foodService.setSelectedDate(date.getValue());
        main.showFoodEmissionScene();
    }

    @FXML
    public void setCow(ObservableValue<Number> ovn, Number before, Number after) {
        cowSliderValue.setText(after.intValue() + " g");
        infoText.setText("");
    }

    @FXML
    public void setPig(ObservableValue<Number> ovn, Number before, Number after) {
        pigSliderValue.setText(after.intValue() + " g");
        infoText.setText("");
    }

    @FXML
    public void setFish(ObservableValue<Number> ovn, Number before, Number after) {
        fishSliderValue.setText(after.intValue() + " g");
        infoText.setText("");
    }

    @FXML
    public void setCheese(ObservableValue<Number> ovn, Number before, Number after) {
        cheeseSliderValue.setText(after.intValue() + " g");
        infoText.setText("");
    }

    @FXML
    public void setRice(ObservableValue<Number> ovn, Number before, Number after) {
        riceSliderValue.setText(after.intValue() + " g");
        infoText.setText("");
    }

    @FXML
    public void setEgg(ObservableValue<Number> ovn, Number before, Number after) {
        eggSliderValue.setText(after.intValue() + " kpl");
        infoText.setText("");
    }

    @FXML
    public void setRestaurant(ObservableValue<Number> ovn, Number before, Number after) {
        restaurantSliderValue.setText(after.intValue() + " €");
        infoText.setText("");
    }

    @FXML
    public void setMilk(ObservableValue<Number> ovn, Number before, Number after) {
        milkSliderValue.setText(after.intValue() + " g");
        infoText.setText("");
    }

    @FXML
    public void setVegetable(ObservableValue<Number> ovn, Number before, Number after) {
        vegetableSliderValue.setText(after.intValue() + " g");
        infoText.setText("");
    }

    @FXML
    public void save() {
        foodService.submit(loginService.getCurrentUser(), foodService.getSelectedDate(),
                (int) cowSlider.getValue(),
                (int) pigSlider.getValue(),
                (int) fishSlider.getValue(),
                (int) cheeseSlider.getValue(),
                (int) riceSlider.getValue(),
                (int) eggSlider.getValue(),
                (int) restaurantSlider.getValue(),
                (int) milkSlider.getValue(),
                (int) vegetableSlider.getValue());
        infoText.setText("Tallennettu!");
    }

    @FXML
    public void submit() {
        foodService.submit(loginService.getCurrentUser(), foodService.getSelectedDate(),
                (int) cowSlider.getValue(),
                (int) pigSlider.getValue(),
                (int) fishSlider.getValue(),
                (int) cheeseSlider.getValue(),
                (int) riceSlider.getValue(),
                (int) eggSlider.getValue(),
                (int) restaurantSlider.getValue(),
                (int) milkSlider.getValue(),
                (int) vegetableSlider.getValue());
        main.showHomeScene();
    }

    @FXML
    public void setDefaultValues() {
        main.showHomeScene();
    }

    @FXML
    public void initialize() {
        String user = loginService.getCurrentUser();
        List<LocalDate> filledDays = foodService.findFilledDays(user, LocalDate.now());

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

        LocalDate selectedDate = foodService.getSelectedDate();
        date.setValue(selectedDate);
        dateText.setText("Täydennä " + selectedDate + " käyttämäsi ruoka-aineet");

        FoodEmission emission = foodService.findEmissionInfo(user, selectedDate);

        int cow = emission.getCow();
        cowSlider.setValue(cow);
        cowSliderValue.setText(cow + " g");

        int pig = emission.getPig();
        pigSlider.setValue(pig);
        pigSliderValue.setText(pig + " g");

        int fish = emission.getFish();
        fishSlider.setValue(fish);
        fishSliderValue.setText(fish + " g");

        int cheese = emission.getCheese();
        cheeseSlider.setValue(cheese);
        cheeseSliderValue.setText(cheese + " g");

        int rice = emission.getRice();
        riceSlider.setValue(rice);
        riceSliderValue.setText(rice + " g");

        int egg = emission.getEgg();
        eggSlider.setValue(egg);
        eggSliderValue.setText(egg + " kpl");

        int restaurant = emission.getRestaurant();
        restaurantSlider.setValue(restaurant);
        restaurantSliderValue.setText(restaurant + " €");

        int milk = emission.getMilk();
        milkSlider.setValue(milk);
        milkSliderValue.setText(milk + " g");

        int vegetable = emission.getVegetable();
        vegetableSlider.setValue(vegetable);
        vegetableSliderValue.setText(vegetable + " g");
    }
}
