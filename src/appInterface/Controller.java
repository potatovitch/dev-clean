package appInterface;

import code.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabGestion, tabAppariement, tabConfiguration;

    @FXML
    private Label botLabel;

    @FXML
    private Pane centerPane;
    
    @FXML
    private TableView<Person> adolescentTableView;
    @FXML
    private TableColumn<Person, String> prenomColumn, nomColumn, paysColumn, criteresColumn;
    
    @FXML
    private TableView<Pair> pairTableView;
    @FXML
    private TableColumn<Pair, String> adolescent1Column, adolescent2Column;
    @FXML
    private TableColumn<Pair, Integer> affiniteColumn;
    
    @FXML
    private Slider hobbySlider, genderSlider, foodSlider, ageSlider;

    @FXML
    private VBox rightVBox;
    
    @FXML
    private Button ajouterButton, modifierButton, supprimerButton, importerButton, exporterButton;
    @FXML
    private Button calculerAppariementButton, exporterPairesButton;

    private Plateform plateform;
    private ObservableList<Person> personnes;
    private ObservableList<Pair> paires;
    
    private static double poidsHobby = 15.0;
    private static double poidsGender = 20.0;
    private static double poidsFood = 10.0;
    private static double poidsAge = 5.0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        plateform = new Plateform();
        initializeTableViews();
        initializeSliders();
        loadData();
        setupTabSelection();
    }

    private void initializeTableViews() {
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        paysColumn.setCellValueFactory(new PropertyValueFactory<>("pays"));
        criteresColumn.setCellValueFactory(cellData -> {
            Person person = cellData.getValue();
            String criteres = person.isHost ? "Hôte" : "Invité";
            return new javafx.beans.property.SimpleStringProperty(criteres);
        });

        adolescent1Column.setCellValueFactory(cellData -> {
            Pair pair = cellData.getValue();
            String nom = pair.getPerson1().getPrenom() + " " + pair.getPerson1().getNom();
            return new javafx.beans.property.SimpleStringProperty(nom);
        });
        
        adolescent2Column.setCellValueFactory(cellData -> {
            Pair pair = cellData.getValue();
            String nom = pair.getPerson2().getPrenom() + " " + pair.getPerson2().getNom();
            return new javafx.beans.property.SimpleStringProperty(nom);
        });
        
        affiniteColumn.setCellValueFactory(cellData -> {
            Pair pair = cellData.getValue();
            int affinite = pair.getPerson1().calculerAffinite(pair.getPerson2());
            return new javafx.beans.property.SimpleIntegerProperty(affinite).asObject();
        });

        personnes = FXCollections.observableArrayList();
        paires = FXCollections.observableArrayList();
        
        adolescentTableView.setItems(personnes);
        pairTableView.setItems(paires);
        
        if (existingPerson != null) {
            prenomField.setText(existingPerson.getPrenom());
            nomField.setText(existingPerson.getNom());
            dateNaissancePicker.setValue(existingPerson.getdNaiss());
            paysCombo.setValue(existingPerson.getPays());
            
            Critere criteres = existingPerson.getCriteres();
            genderCombo.setValue(criteres.getGender());
            pairGenderCombo.setValue(criteres.getPairGender());
            isHostCheckBox.setSelected(existingPerson.isHost);
        }

        if (existingPerson != null) {
            // Mise à jour des propriétés de la personne existante
            existingPerson.setNom(nom);
            existingPerson.setPrenom(prenom);
            existingPerson.setDNaiss(dateNaissance);
            existingPerson.setPays(pays);
            return existingPerson;
        }
    }

    private void initializeSliders() {
        hobbySlider.setValue(poidsHobby);
        genderSlider.setValue(poidsGender);
        foodSlider.setValue(poidsFood);
        ageSlider.setValue(poidsAge);
        
        hobbySlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            poidsHobby = newVal.doubleValue();
            updateBotLabel("Poids hobby mis à jour: " + String.format("%.1f", poidsHobby));
        });
        
        genderSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            poidsGender = newVal.doubleValue();
            updateBotLabel("Poids genre mis à jour: " + String.format("%.1f", poidsGender));
        });
        
        foodSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            poidsFood = newVal.doubleValue();
            updateBotLabel("Poids nourriture mis à jour: " + String.format("%.1f", poidsFood));
        });
        
        ageSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            poidsAge = newVal.doubleValue();
            updateBotLabel("Poids âge mis à jour: " + String.format("%.1f", poidsAge));
        });
    }

    private void setupTabSelection() {
        tabPane.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            if (newTab == tabGestion) {
                refreshPersonnesTable();
            } else if (newTab == tabAppariement) {
                refreshPairesTable();
            }
        });
    }

    private void loadData() {
        refreshPersonnesTable();
        refreshPairesTable();
        updateBotLabel("Données chargées");
    }

    private void refreshPersonnesTable() {
        personnes.clear();
        personnes.addAll(plateform.getListPersonnes());
    }

    private void refreshPairesTable() {
        paires.clear();
        for (Pair pair : plateform.getListPairs()) {
            paires.add(pair);
        }
    }

    private void updateBotLabel(String message) {
        if (botLabel != null) {
            botLabel.setText(message);
        }
    }

    /**
     * Tab contenant les éléments nécessaires à la modification, l'ajout d'adolescent ainsi qu'à l'import et à l'export de fichiers CSV contenant ces adolescent.
     * @param event
     */
    @FXML
    public void gestionTabPressed(ActionEvent event) {
        refreshPersonnesTable();
        updateBotLabel("Onglet Gestion sélectionné");
    }

    /**
     * Tab contenant les éléments nécessaires à la gestion des appariements.
     * @param event
     */
    @FXML
    public void appariementTabPressed(ActionEvent event) {
        refreshPairesTable();
        updateBotLabel("Onglet Appariement sélectionné");
    }

    /**
     * Tab contenant les sliders utile à la selection des critères les plus importants. 
     * @param event
     */
    @FXML
    public void configurationTabPressed(ActionEvent event) {
        updateBotLabel("Onglet Configuration sélectionné");
    }

    /**
     * Bouton permettant d'ajouter un adolescent à la liste en cours à l'aide d'une boite de dialog.
    */
    @FXML
    public void ajouterButtonPressed(ActionEvent event) {
        Dialog<Person> dialog = createPersonDialog(null);
        Optional<Person> result = dialog.showAndWait();
        
        if (result.isPresent()) {
            Person newPerson = result.get();
            plateform.addPerson(newPerson);
            refreshPersonnesTable();
            updateBotLabel("Personne ajoutée: " + newPerson.getPrenom() + " " + newPerson.getNom());
        }
    }

    /**
     * Bouton permettant de modifier l'adolescent selectionner.
     * @param event
     */
    public void modifierButtonPressed(ActionEvent event) {
        Person selectedPerson = adolescentTableView.getSelectionModel().getSelectedItem();
        if (selectedPerson == null) {
            updateBotLabel("Veuillez sélectionner une personne à modifier");
            return;
        }

        Dialog<Person> dialog = createPersonDialog(selectedPerson);
        Optional<Person> result = dialog.showAndWait();
        
        if (result.isPresent()) {
            refreshPersonnesTable();
            updateBotLabel("Personne modifiée: " + selectedPerson.getPrenom() + " " + selectedPerson.getNom());
        }
    }

    /**
     * Bouton permettant de supprimer de la liste des adolescent l'adolescent selectionner.
     * @param event
     */
    public void supprimerButtonPressed(ActionEvent event) {
        Person selectedPerson = adolescentTableView.getSelectionModel().getSelectedItem();
        if (selectedPerson == null) {
            updateBotLabel("Veuillez sélectionner une personne à supprimer");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Supprimer la personne");
        alert.setContentText("Êtes-vous sûr de vouloir supprimer " + 
                           selectedPerson.getPrenom() + " " + selectedPerson.getNom() + " ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            plateform.removePerson(selectedPerson);
            refreshPersonnesTable();
            updateBotLabel("Personne supprimée: " + selectedPerson.getPrenom() + " " + selectedPerson.getNom());
        }
    }

    /**
     * Bouton permettant l'import d'un fichier au format CSV contenant des adolescents.
     * @param event
     */
    public void importerButtonPressed(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Importer fichier CSV");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Fichiers CSV", "*.csv")
        );
        
        Stage stage = (Stage) importerButton.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        
        if (selectedFile != null) {
            try {
                ArrayList<Person> importedPersons = plateform.loadPersonsFromCSV(selectedFile.getAbsolutePath());
                for (Person p : importedPersons) {
                    plateform.addPerson(p);
                }
                refreshPersonnesTable();
                updateBotLabel("Importation réussie: " + importedPersons.size() + " personnes importées");
            } catch (Exception e) {
                updateBotLabel("Erreur lors de l'importation: " + e.getMessage());
            }
        }
    }

    /**
     * Bouton permettant l'export au format CSV d'une liste d'adolescent modifier ou non.
     * @param event
     */
    public void exporterButtonPressed(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Exporter vers CSV");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Fichiers CSV", "*.csv")
        );
        
        Stage stage = (Stage) exporterButton.getScene().getWindow();
        File selectedFile = fileChooser.showSaveDialog(stage);
        
        if (selectedFile != null) {
            try {
                CSVHandler csvHandler = new CSVHandler();
                csvHandler.savePersonsToCSV(selectedFile.getAbsolutePath());
                updateBotLabel("Exportation réussie vers: " + selectedFile.getName());
            } catch (Exception e) {
                updateBotLabel("Erreur lors de l'exportation: " + e.getMessage());
            }
        }
    }

    /**
     * Bouton permettant de calculer les paires possibles dans le CSV proposé.
     */
    @FXML
    public void calculerAppariementPressed(ActionEvent event) {
        try {
            plateform.appariementOptimise();
            refreshPairesTable();
            updateBotLabel("Appariement calculé avec succès");
        } catch (Exception e) {
            updateBotLabel("Erreur lors du calcul d'appariement: " + e.getMessage());
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
                updateBotLabel("Paires exportées vers: " + selectedFile.getName());
            } catch (Exception e) {
                updateBotLabel("Erreur lors de l'exportation des paires: " + e.getMessage());
            }
        }
    }

    /**
     * Création d'une boîte de dialogue afin d'ajouter un nouvel adolescent au CSV.
     * @param existingPerson
     * @return
     */
    private Dialog<Person> createPersonDialog(Person existingPerson) {
        Dialog<Person> dialog = new Dialog<>();
        dialog.setTitle(existingPerson == null ? "Ajouter une personne" : "Modifier une personne");
        dialog.setHeaderText("Saisir les informations de la personne\n(toutes les informations sont obligatoires)");

        TextField prenomField = new TextField();
        TextField nomField = new TextField();
        DatePicker dateNaissancePicker = new DatePicker();
        ComboBox<Pays> paysCombo = new ComboBox<>();
        paysCombo.getItems().setAll(Pays.values());
        ComboBox<Gender> genderCombo = new ComboBox<>();
        genderCombo.getItems().setAll(Gender.values());
        ComboBox<Gender> pairGenderCombo = new ComboBox<>();
        pairGenderCombo.getItems().setAll(Gender.values());
        CheckBox isHostCheckBox = new CheckBox("Est un hôte");

        if (existingPerson != null) {
            prenomField.setText(existingPerson.getPrenom());
            nomField.setText(existingPerson.getNom());
            dateNaissancePicker.setValue(existingPerson.getdNaiss());
            paysCombo.setValue(existingPerson.getPays());
        }

        VBox content = new VBox(10);
        content.getChildren().addAll(
            new Label("Prénom:"), prenomField,
            new Label("Nom:"), nomField,
            new Label("Date de naissance:"), dateNaissancePicker,
            new Label("Pays:"), paysCombo,
            new Label("Genre:"), genderCombo,
            new Label("Genre recherché:"), pairGenderCombo,
            isHostCheckBox
        );

        dialog.getDialogPane().setContent(content);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                try {
                    String prenom = prenomField.getText().trim();
                    String nom = nomField.getText().trim();
                    LocalDate dateNaissance = dateNaissancePicker.getValue();
                    Pays pays = paysCombo.getValue();
                    Gender gender = genderCombo.getValue();
                    Gender pairGender = pairGenderCombo.getValue();
                    boolean isHost = isHostCheckBox.isSelected();

                    if (prenom.isEmpty() || nom.isEmpty() || dateNaissance == null || 
                        pays == null || gender == null || pairGender == null) {
                        updateBotLabel("Veuillez remplir tous les champs obligatoires");
                        return null;
                    }

                    if (isHost) {
                        return new Host(nom, prenom, dateNaissance, pays, gender, pairGender, false, new ArrayList<>());
                    } else {
                        return new Guest(nom, prenom, dateNaissance, pays, gender, pairGender, false, new ArrayList<>());
                    }
                } catch (Exception e) {
                    updateBotLabel("Erreur lors de la création de la personne: " + e.getMessage());
                    return null;
                }
            }
            return null;
        });

        return dialog;
    }

    /**
     * Getters des poids renvoyer par les sliders.
     * @return
     */
    public static double getPoidsHobby() { 
        return poidsHobby; 
    }
    public static double getPoidsGender() { 
        return poidsGender; 
    }
    public static double getPoidsFood() { 
        return poidsFood; 
    }
    public static double getPoidsAge() { 
        return poidsAge; 
    }
}