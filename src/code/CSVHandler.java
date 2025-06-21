package code;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CSVHandler {

    /**
     * sauvegarde les personnes de la plateforme dans un fichier CSV et les paires
     */
    public void saveToCSV(String filePath) {
        savePersonsToCSV(filePath);
        savePairsToCSV(filePath);
    }

    public void savePersonsToCSV(String filePath){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Nom, Prénom, Date de Naissance, Pays\n");
            for (Person p : Plateform.getListPersonnesStatic()) {
                writer.write(p.getNom() + ", " + p.getPrenom() + ", " + p.getdNaiss() + ", " + p.getPays() + "\n");
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void savePairsToCSV(String filePath){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            for (Pair p : Plateform.getListPairsStatic()) {
                writer.write(p.getPerson1().getNom() + ", " + p.getPerson1().getNom() + "\n");
            }
            writer.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }


    /**
     * charge les personnes et les paires depuis un fichier CSV
     */
    public void loadFromCSV(String filePath) {
        loadPairsFromCSV(filePath);
        loadPersonsFromCSV(filePath);
    }

    public static ArrayList<Person> loadPersonsFromCSV(String filePath) {
        ArrayList<Person> persons = new ArrayList<Person>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] cells = line.split(", ");
                if (cells.length == 9) {    // neuf colonnes attendues
                    String nom = cells[0];  // premier élément : nom
                    String prenom = cells[1]; // deuxième élément : prénom
                    LocalDate dNaiss = LocalDate.parse(cells[2]);   // troisième élément : date de naissance
                    Pays pays = Pays.valueOf(cells[3]); // quatrième élément : pays
                    boolean isHost = Boolean.parseBoolean(cells[4]);      // cinquieme élément : est un host ?
                    Gender gender = Gender.valueOf(cells[5]);             // sixieme element : genre de la personne
                    Gender pairGender = Gender.valueOf(cells[6]);         // septieme element : genre du correspondant
                    boolean animal = Boolean.parseBoolean(cells[7]);      // huitieme element : hasAnimal ou hasAllergy (en foncion de la personne)
                    ArrayList<String> listFood = new ArrayList<String>();       // neuvieme element : GuestFood ou HostFood (en foncion de la personne)
                    String[] temp = cells[8].split(", ");
                    for (String food : temp) {
                        listFood.add(food);
                    }

                    if (isHost){
                        persons.add(new Host(nom, prenom, dNaiss, pays, gender, pairGender, animal, listFood)); 
                    }else{
                        persons.add(new Guest(nom, prenom, dNaiss, pays, gender, pairGender, animal, listFood));
                    }
                }
            }
            reader.close();
        } catch (Exception e) {
            e.getMessage();
        }
        return persons;
    }

    public static ArrayList<Pair> loadPairsFromCSV(String filePath) {
        ArrayList<Pair> pairs = new ArrayList<Pair>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] cells = line.split(", ");
                if (cells.length == 2) {    // deux colonnes attendues
                    String nom1 = cells[0];  // premier élément : nom de la première personne
                    String nom2 = cells[1];  // deuxième élément : nom de la deuxième personne
                    Person person1 = Plateform.findPersonByNameStatic(nom1);
                    Person person2 = Plateform.findPersonByNameStatic(nom2);
                    if (person1 != null && person2 != null) {
                        pairs.add(new Pair(person1, person2));
                    }
                }
            }
            reader.close();
        } catch (Exception e) {
            e.getMessage();
        }
        return pairs;
    }

}
