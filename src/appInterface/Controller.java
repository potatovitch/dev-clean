package appInterface;   // pas interface java aime pas

import code.Pair;
import code.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Controller {
    @FXML
    /* top */
    Tab gestionTab, appariementTab, configurationTab;

    /* bot */
    Label botLabel;

    /* center Pane */
    Pane centerPane;        // jsp si on le garde ou si on peut faire sans stocker les tableview dedans
    // Gestion des adolescents      (table d'affichage des etudiants)
    TableView<Person> adolescentTableView;
    ColumnConstraints prenom, nom, pays, criteres;      
    // Appariement                  (table d'affichage des paires)
    TableView<Pair> pairTableView;     
    ColumnConstraints adolescent1, adolescent2, affinite; 
    // Configuration                (sliders des poids)
    Slider hobbySlider, genderSlider, foodSlider, ageSlider;

    /* right vbox */
    VBox rightVBox;
    // Gestion des adolescents
    Button ajouterButton, modifierButton, supprimerButton, importerButton, exporterButton;
    // Appariement
    Button calculerAppariementButton, exporterPairesButton;
    

    
    /* gestion des Tabs */ 

    public void gestionTabPressed(ActionEvent event) {
        // TODO
    }
    public void appariementTabPressed(ActionEvent event) {
        // TODO
    }
    public void configurationTabPressed(ActionEvent event) {
        // TODO
    }



    /* gestion du right vbox */

    // Gestion des adolescents
    public void ajouterButtonPressed(ActionEvent event) {
        // TODO
    }
    public void modifierButtonPressed(ActionEvent event) {
        // TODO
    }
    public void supprimerButtonPressed(ActionEvent event) {
        // TODO
    }
    public void importerButtonPressed(ActionEvent event) {
        // TODO
    }
    public void exporterButtonPressed(ActionEvent event) {
        // TODO
    }

    // Appariement
    public void calculerAppariementPressed(ActionEvent event) {
        // TODO
    }
    public void exporterPairesPressed(ActionEvent event) {
        // TODO
    }

    // Configuration
    public void hobbySlided(ActionEvent event) {
        // TODO
    }
    public void genderSlided(ActionEvent event) {
        // TODO
    }
    public void foodSlided(ActionEvent event) {
        // TODO
    }
    public void ageSlided(ActionEvent event) {
        // TODO
    }
}
