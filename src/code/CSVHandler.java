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
                StringBuilder foodList = new StringBuilder();
                if (p.isHost) {
                    HostCritere crit = (HostCritere) p.getCriteres();
                    for (Food food : crit.getListHostFood()) {
                        if (foodList.length() > 0) foodList.append(";");
                        foodList.append(food);
                    }
                    writer.write(p.getNom() + "," + p.getPrenom() + "," + p.getdNaiss() + "," + 
                               p.getPays() + ",true," + crit.gender + "," + crit.pairGender + "," + 
                               crit.aUnAnimal() + "," + foodList.toString() + "\n");
                } else {
                    GuestCritere crit = (GuestCritere) p.getCriteres();
                    for (Food food : crit.getListGuestFood()) {
                        if (foodList.length() > 0) foodList.append(";");
                        foodList.append(food);
                    }
                    writer.write(p.getNom() + "," + p.getPrenom() + "," + p.getdNaiss() + "," + 
                               p.getPays() + ",false," + crit.gender + "," + crit.pairGender + "," + 
                               crit.estAllergique() + "," + foodList.toString() + "\n");
                }
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
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] cells = line.split(",");
                if (cells.length >= 8) {
                    String nom = cells[0].trim();
                    String prenom = cells[1].trim();
                    LocalDate dNaiss = LocalDate.parse(cells[2].trim());
                    Pays pays = Pays.valueOf(cells[3].trim());
                    boolean isHost = Boolean.parseBoolean(cells[4].trim());
                    Gender gender = Gender.valueOf(cells[5].trim());
                    Gender pairGender = Gender.valueOf(cells[6].trim());
                    boolean animalOrAllergy = Boolean.parseBoolean(cells[7].trim());
                    
                    ArrayList<String> listFood = new ArrayList<String>();
                    if (cells.length > 8 && !cells[8].trim().isEmpty()) {
                        String[] foods = cells[8].trim().split(";");
                        for (String food : foods) {
                            if (!food.trim().isEmpty()) {
                                listFood.add(food.trim());
                            }
                        }
                    }

                    if (isHost){                // creation des personnes
                        persons.add(new Host(nom, prenom, dNaiss, pays, gender, pairGender, animal, listFood)); 
                    }else{
                        persons.add(new Guest(nom, prenom, dNaiss, pays, gender, pairGender, animal, listFood));
                    }
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