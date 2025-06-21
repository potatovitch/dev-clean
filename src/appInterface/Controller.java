package appInterface;   // pas interface java aime pas

import javax.swing.event.ChangeListener;
import code.Pair;
import code.Person;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
        Label label = new Label("Poids du hobby :");
        Label valHob = new Label(" ");
        Slider hobby = new Slider(0,20,10);
        hobby.setShowTickMarks(true);
        hobby.setShowTickLabels(true);
        hobby.setMajorTickUnit(5f);
        hobby.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue <? extends Number > observable, Number oldValue, Number newValue)
            {

                valGen.setText("value: " + newValue);
            }
        });

        HBox valBox = new HBox();
        valBox.setPadding(new Insets(20));
        valBox.setSpacing(10);
        valBox.getChildren().addAll(label, hobby, valHob);
    }
    public void genderSlided(ActionEvent event) {
        Label label = new Label("Poids du genre :");
        Label valGen = new Label(" ");
        Slider gender = new Slider(0,20,10);
        gender.setShowTickMarks(true);
        gender.setShowTickLabels(true);
        gender.setMajorTickUnit(5f);
        gender.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue <? extends Number > observable, Number oldValue, Number newValue)
            {

                valGen.setText("value: " + newValue);
            }
        });

        HBox genBox = new HBox();
        genBox.setPadding(new Insets(20));
        genBox.setSpacing(10);
        genBox.getChildren().addAll(label, gender, valGen);
    }
    public void foodSlided(ActionEvent event) {
        Label label = new Label("Poids de l'alimentation : ");
        Label valFoo = new Label(" ");
        Slider food = new Slider(0,20,10);
        food.setShowTickMarks(true);
        food.setShowTickLabels(true);
        food.setMajorTickUnit(5f);
        food.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue <? extends Number > observable, Number oldValue, Number newValue)
            {

                valGen.setText("value: " + newValue);
            }
        });

        HBox fooBox = new HBox();
        fooBox.setPadding(new Insets(20));
        fooBox.setSpacing(10);
        fooBox.getChildren().addAll(label, food, valFoo);
    }
    public void ageSlided(ActionEvent event) {
        Label label = new Label("Poids de l'âge : ");
        Label valAge = new Label(" ");
        Slider age = new Slider(0,20,10);
        age.setShowTickMarks(true);
        age.setShowTickLabels(true);
        age.setMajorTickUnit(5f);
        age.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue <? extends Number > observable, Number oldValue, Number newValue)
            {

                valGen.setText("value: " + newValue);
            }
        });
        
        HBox ageBox = new HBox();
        ageBox.setPadding(new Insets(20));
        ageBox.setSpacing(10);
        ageBox.getChildren().addAll(label, age, valAge);
    }
}
