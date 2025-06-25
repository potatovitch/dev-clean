package appInterface;

import java.io.File;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import code.CSVHandler;
import code.Pair;
import code.Plateform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AppariementController {

    @FXML private TableView<Pair> pairTableView;
    @FXML private TableColumn<Pair, String> adolescent1Column, adolescent2Column;
    @FXML private TableColumn<Pair, Integer> affiniteColumn;

    @FXML private Button calculerAppariementButton, exporterPairesButton;

    private Plateform plateform = new Plateform();
    private ObservableList<Pair> paires = FXCollections.observableArrayList();

    private MainController mainController;

    @FXML
    public void initialize() {
        adolescent1Column.setCellValueFactory(cellData -> new SimpleStringProperty(
            cellData.getValue().getPerson1().getPrenom() + " " + cellData.getValue().getPerson1().getNom()
        ));
        adolescent2Column.setCellValueFactory(cellData -> new SimpleStringProperty(
            cellData.getValue().getPerson2().getPrenom() + " " + cellData.getValue().getPerson2().getNom()
        ));
        affiniteColumn.setCellValueFactory(cellData -> 
            new SimpleIntegerProperty(cellData.getValue().getPerson1().calculerAffinite(cellData.getValue().getPerson2())).asObject()
        );

        pairTableView.setItems(paires);

        refreshPairesTable();
    }

    public void setMainController(MainController mc) {
        this.mainController = mc;
    }

    public void refreshPairesTable() {
        paires.clear();
        paires.addAll(plateform.getListPairs());
    }

    @FXML
    public void calculerAppariementPressed(ActionEvent event) {
        try {
            plateform.appariementOptimise();
            refreshPairesTable();
            mainController.updateBotLabel("Appariement calculé avec succès");
        } catch (Exception e) {
            mainController.updateBotLabel("Erreur lors du calcul d'appariement: " + e.getMessage());
        }
    }

    /**
     * Bouton permettant l'export des paires créer dans un fichier au format CSV.
     * @param event
     */
    @FXML
    public void exporterPairesPressed(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Exporter paires vers CSV");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Fichiers CSV", "*.csv")
        );
        
        Stage stage = (Stage) exporterPairesButton.getScene().getWindow();
        File selectedFile = fileChooser.showSaveDialog(stage);
        
        if (selectedFile != null) {
            try {
                CSVHandler csvHandler = new CSVHandler();
                csvHandler.savePairsToCSV(selectedFile.getAbsolutePath());
                mainController.updateBotLabel("Paires exportées vers: " + selectedFile.getName());
            } catch (Exception e) {
                mainController.updateBotLabel("Erreur lors de l'exportation des paires: " + e.getMessage());
            }
        }
    }
}
