package tests;

import code.CSVHandler;
import code.Person;
import code.Pair;
import code.Plateform;
import code.Critere;
import code.Pays;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

public class CSVHandlerTest {

    public static void main(String[] args) {
        CSVHandler csvHandler = new CSVHandler();

        ArrayList<String> guestFood = new ArrayList<>();
        guestFood.add("Vegan");
        guestFood.add("No Gluten");

        ArrayList<String> hostFood = new ArrayList<>();
        hostFood.add("Vegetarian");

        ArrayList<String> hobbies = new ArrayList<>();
        hobbies.add("Football");
        hobbies.add("Reading");

        Critere critere1 = new Critere("M", "F", false, true, guestFood, hostFood, hobbies, hobbies);
        Critere critere2 = new Critere("F", "M", true, false, guestFood, hostFood, hobbies, hobbies);

        Person p1 = new Person("Dupont", "Jean", LocalDate.of(1990, 5, 12), Pays.FRANCE, true, critere1);
        Person p2 = new Person("Martin", "Alice", LocalDate.of(1992, 8, 23), Pays.FRANCE, false, critere2);

        Plateform.getListPersonnesStatic().clear();
        Plateform.getListPersonnesStatic().add(p1);
        Plateform.getListPersonnesStatic().add(p2);

        Pair pair = new Pair(p1, p2);
        Plateform.getListPairsStatic().clear();
        Plateform.getListPairsStatic().add(pair);

        String filePath = "test_output.csv";

        csvHandler.saveToCSV(filePath);
        System.out.println("Sauvegarde terminée dans : " + filePath);

        ArrayList<Person> loadedPersons = CSVHandler.loadPersonsFromCSV(filePath);
        System.out.println("Personnes chargées :");
        for (Person person : loadedPersons) {
            System.out.println(person.getPrenom() + " " + person.getNom() + " né(e) le " + person.getdNaiss());
        }

        ArrayList<Pair> loadedPairs = CSVHandler.loadPairsFromCSV(filePath);
        System.out.println("Paires chargées :");
        for (Pair p : loadedPairs) {
            System.out.println(p.getPerson1().getPrenom() + " - " + p.getPerson2().getPrenom());
        }

        File f = new File(filePath);
        if (f.delete()) {
            System.out.println("Fichier de test supprimé.");
        }
    }
}
