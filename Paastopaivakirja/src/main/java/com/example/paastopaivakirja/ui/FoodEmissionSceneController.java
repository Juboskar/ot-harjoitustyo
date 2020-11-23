package com.example.paastopaivakirja.ui;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

/**
 *
 * @author Oskari
 */
@Component
@FxmlView("/fxml/FoodEmissionScene.fxml")
public class FoodEmissionSceneController {

    @FXML
    Slider cowSlider;

    @FXML
    Slider pigSlider;

    @FXML
    Slider fishSlider;

    @FXML
    Slider CheeseSlider;

    @FXML
    Slider riceSlider;

    @FXML
    Slider eggSlider;
    
    @FXML
    Slider restaurantSlider;

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
    public void setCow(ObservableValue<Number> ovn, Number before, Number after) {
        cowSliderValue.setText(after.intValue() + " g");
    }

    @FXML
    public void setPig(ObservableValue<Number> ovn, Number before, Number after) {
        pigSliderValue.setText(after.intValue() + " g");
    }

    @FXML
    public void setFish(ObservableValue<Number> ovn, Number before, Number after) {
        fishSliderValue.setText(after.intValue() + " g");
    }

    @FXML
    public void setCheese(ObservableValue<Number> ovn, Number before, Number after) {
        cheeseSliderValue.setText(after.intValue() + " g");
    }

    @FXML
    public void setRice(ObservableValue<Number> ovn, Number before, Number after) {
        riceSliderValue.setText(after.intValue() + " g");
    }
    
    @FXML
    public void setEgg(ObservableValue<Number> ovn, Number before, Number after) {
        eggSliderValue.setText(after.intValue() + " kpl");
    }
    
    @FXML
    public void setRestaurant(ObservableValue<Number> ovn, Number before, Number after) {
        restaurantSliderValue.setText(after.intValue() + " kpl");
    }

}
