package appInterface;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;

public class ConfigurationController {

    @FXML private Slider hobbySlider, genderSlider, foodSlider, ageSlider;
    private MainController mainController;

    static double poidsHobby = 15.0;
    static double poidsGender = 20.0;
    static double poidsFood = 10.0;
    static double poidsAge = 5.0;

    @FXML
    public void initialize() {
        hobbySlider.setValue(poidsHobby);
        genderSlider.setValue(poidsGender);
        foodSlider.setValue(poidsFood);
        ageSlider.setValue(poidsAge);

        hobbySlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            poidsHobby = newVal.doubleValue();
            mainController.updateBotLabel("Poids hobby mis à jour: " + String.format("%.1f", poidsHobby));
        });

        genderSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            poidsGender = newVal.doubleValue();
            mainController.updateBotLabel("Poids genre mis à jour: " + String.format("%.1f", poidsGender));
        });

        foodSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            poidsFood = newVal.doubleValue();
            mainController.updateBotLabel("Poids nourriture mis à jour: " + String.format("%.1f", poidsFood));
        });

        ageSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            poidsAge = newVal.doubleValue();
            mainController.updateBotLabel("Poids âge mis à jour: " + String.format("%.1f", poidsAge));
        });
    }

    public void setMainController(MainController mc) {
        this.mainController = mc;
    }

    // Getters statiques pour ces poids
    public static double getPoidsHobby() { return poidsHobby; }
    public static double getPoidsGender() { return poidsGender; }
    public static double getPoidsFood() { return poidsFood; }
    public static double getPoidsAge() { return poidsAge; }
}

