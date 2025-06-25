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
            writer.write("Nom,Prénom,Date de Naissance,Pays,Est Hôte,Genre,Genre Recherché,Animal/Allergie,Nourriture\n");
            for (Person p : Plateform.getListPersonnesStatic()) {
                Critere crit = p.critere;
                writer.write(p.getNom() + ";" + p.getPrenom() + ";" + p.getdNaiss() + ";" + p.getPays() + Person.toStringBool(p.isHost()) + crit.gender + ";" + crit.pairGender + ";" + 
                               Person.toStringBool(crit.hasAllergy) + ";" + Person.toStringBool(crit.hasAnimal) + ";" + crit.toStringGuestFoodConstraint() + ";"
                               + crit.toStringHostFood() + ";"+ crit.toStringHobbies() + ";"+ crit.toStringHistory()
                               + "\n");
                
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde des personnes: " + e.getMessage());
        }
    }

    public void savePairsToCSV(String filePath){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Personne 1,Personne 2,Affinité\n");
            for (Pair p : Plateform.getListPairsStatic()) {
                int affinite = p.calculerAffinite(p.getPerson1(), p.getPerson2());
                writer.write(p.getPerson1().getPrenom() + " " + p.getPerson1().getNom() + "," + 
                           p.getPerson2().getPrenom() + " " + p.getPerson2().getNom() + "," + 
                           affinite + "\n");
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde des paires: " + e.getMessage());
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
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] cells = line.split(", ");
                if (cells.length == 13) {    // dix colonnes attendues
                    String nom = cells[0];  // premier élément : nom
                    String prenom = cells[1]; // deuxième élément : prénom
                    LocalDate dNaiss = LocalDate.parse(cells[2]);   // troisième élément : date de naissance
                    Pays pays = Pays.valueOf(cells[3]); // quatrième élément : pays
                    boolean isHost;          // cinquieme élément : est un host ?
                    if (cells[4].equals("yes")){
                        isHost = true;
                    }else{
                        isHost = false;
                    }
                    String gender = cells[5];             // sixieme element : genre de la personne
                    String pairGender = cells[6];         // septieme element : genre du correspondant
                    boolean hasAllergy = Person.toBoolString(cells[7]);     
                    boolean hasAnimal = Person.toBoolString(cells[8]);

                    ArrayList<String> listGuestFoodConstraint = new ArrayList<String>();       
                    String[] tempConstraint = cells[9].split(", ");
                    for (String food : tempConstraint) {
                        listGuestFoodConstraint.add(food);
                    }

                    ArrayList<String> listHostFood = new ArrayList<String>();       
                    String[] tempHostFood = cells[10].split(", ");
                    for (String food : tempHostFood) {
                        listHostFood.add(food);
                    }

                    ArrayList<String> listHobbies = new ArrayList<String>();      
                    String[] tempHobby = cells[11].split(", ");
                    for (String hobby : tempHobby) {
                        listHobbies.add(hobby);
                    }


                    ArrayList<String> listHistory = new ArrayList<String>();       
                    String[] tempHistory = cells[12].split(", ");
                    for (String history : tempHistory) {
                        listHistory.add(history);
                    }

                    // creation des personnes
                    persons.add(new Person(nom, prenom, dNaiss, pays, isHost, new Critere(gender, pairGender, hasAllergy, hasAnimal,
                                                                listGuestFoodConstraint, listHostFood, listHobbies, listHobbies)));
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement des personnes: " + e.getMessage());
        }
        return persons;
    }

    public static ArrayList<Pair> loadPairsFromCSV(String filePath) {
        ArrayList<Pair> pairs = new ArrayList<Pair>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] cells = line.split(",");
                if (cells.length >= 2) {
                    String nom1 = cells[0].trim();
                    String nom2 = cells[1].trim();
                    Person person1 = Plateform.findPersonByNameStatic(nom1);
                    Person person2 = Plateform.findPersonByNameStatic(nom2);
                    if (person1 != null && person2 != null) {
                        pairs.add(new Pair(person1, person2));
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement des paires: " + e.getMessage());
        }
        return pairs;
    }
}