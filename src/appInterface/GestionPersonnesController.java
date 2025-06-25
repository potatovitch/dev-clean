package appInterface;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import code.CSVHandler;
import code.Gender;
import code.Guest;
import code.Host;
import code.Pays;
import code.Person;
import code.Plateform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GestionPersonnesController {

    @FXML private TableView<Person> adolescentTableView;
    @FXML private TableColumn<Person, String> prenomColumn, nomColumn, paysColumn, criteresColumn;

    @FXML private Button ajouterButton, modifierButton, supprimerButton, importerButton, exporterButton;

    private Plateform plateform = new Plateform();
    private ObservableList<Person> personnes = FXCollections.observableArrayList();

    private MainController mainController;

    @FXML
    public void initialize() {
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        paysColumn.setCellValueFactory(new PropertyValueFactory<>("pays"));
        criteresColumn.setCellValueFactory(cellData -> {
            Person p = cellData.getValue();
            return new SimpleStringProperty(p.isHost() ? "Hôte" : "Invité");
        });


        adolescentTableView.setItems(personnes);

        refreshPersonnesTable();
    }

    public void setMainController(MainController mc) {
        this.mainController = mc;
    }

    public void refreshPersonnesTable() {
        personnes.clear();
        personnes.addAll(plateform.getListPersonnes());
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
            mainController.updateBotLabel("Personne ajoutée: " + newPerson.getPrenom() + " " + newPerson.getNom());
        }
    }

     /**
     * Bouton permettant de modifier l'adolescent selectionner.
     * @param event
     */
    public void modifierButtonPressed(ActionEvent event) {
        Person selectedPerson = adolescentTableView.getSelectionModel().getSelectedItem();
        if (selectedPerson == null) {
            mainController.updateBotLabel("Veuillez sélectionner une personne à modifier");
            return;
        }

        Dialog<Person> dialog = createPersonDialog(selectedPerson);
        Optional<Person> result = dialog.showAndWait();
        
        if (result.isPresent()) {
            refreshPersonnesTable();
            mainController.updateBotLabel("Personne modifiée: " + selectedPerson.getPrenom() + " " + selectedPerson.getNom());
        }
    }

    /**
     * Bouton permettant de supprimer de la liste des adolescent l'adolescent selectionner.
     * @param event
     */
    public void supprimerButtonPressed(ActionEvent event) {
        Person selectedPerson = adolescentTableView.getSelectionModel().getSelectedItem();
        if (selectedPerson == null) {
            mainController.updateBotLabel("Veuillez sélectionner une personne à supprimer");
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
            mainController.updateBotLabel("Personne supprimée: " + selectedPerson.getPrenom() + " " + selectedPerson.getNom());
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
                mainController.updateBotLabel("Importation réussie: " + importedPersons.size() + " personnes importées");
            } catch (Exception e) {
                mainController.updateBotLabel("Erreur lors de l'importation: " + e.getMessage());
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
                mainController.updateBotLabel("Exportation réussie vers: " + selectedFile.getName());
            } catch (Exception e) {
                mainController.updateBotLabel("Erreur lors de l'exportation: " + e.getMessage());
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
                        mainController.updateBotLabel("Veuillez remplir tous les champs obligatoires");
                        return null;
                    }

                    if (isHost) {
                        return new Host(nom, prenom, dateNaissance, pays, gender, pairGender, false, new ArrayList<>());
                    } else {
                        return new Guest(nom, prenom, dateNaissance, pays, gender, pairGender, false, new ArrayList<>());
                    }
                } catch (Exception e) {
                    mainController.updateBotLabel("Erreur lors de la création de la personne: " + e.getMessage());
                    return null;
                }
            }
            return null;
        });

        return dialog;
    }
}
