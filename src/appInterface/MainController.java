package appInterface;

import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainController implements Initializable {

    @FXML private TabPane tabPane;
    @FXML private Label botLabel;

    @FXML private Tab tabGestion;
    @FXML private Tab tabAppariement;
    @FXML private Tab tabConfiguration;
    
    @FXML private GestionPersonnesController gestionPersonnesController;
    @FXML private AppariementController appariementController;
    @FXML private ConfigurationController configurationController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tabPane.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            if (newTab == tabGestion) {
                gestionPersonnesController.refreshPersonnesTable();
                updateBotLabel("Onglet Gestion sélectionné");
            } else if (newTab == tabAppariement) {
                appariementController.refreshPairesTable();
                updateBotLabel("Onglet Appariement sélectionné");
            } else if (newTab == tabConfiguration) {
                updateBotLabel("Onglet Configuration sélectionné");
            }
        });

        gestionPersonnesController.setMainController(this);
        appariementController.setMainController(this);
        configurationController.setMainController(this);
    }

    public void updateBotLabel(String message) {
        botLabel.setText(message);
    }

    /**
     * Getters des poids renvoyer par les sliders.
     * @return
     */
    public static double getPoidsHobby() { 
        return ConfigurationController.poidsHobby; 
    }
    public static double getPoidsGender() { 
        return ConfigurationController.poidsGender; 
    }
    public static double getPoidsFood() { 
        return ConfigurationController.poidsFood; 
    }
    public static double getPoidsAge() { 
        return ConfigurationController.poidsAge; 
    }
}
