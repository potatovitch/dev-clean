package tests;

import code.Person;
import code.Plateform;
import code.Critere;
import code.Pays;

import java.time.LocalDate;
import java.util.ArrayList;

public class PlateformTest {

    public static void main(String[] args) {
        Plateform plateform = new Plateform();

        ArrayList<String> guestFood = new ArrayList<>();
        guestFood.add("Vegetarian");

        ArrayList<String> hostFood = new ArrayList<>();
        hostFood.add("Vegetarian");

        ArrayList<String> hobbies = new ArrayList<>();
        hobbies.add("Reading");

        Critere crit = new Critere("M", "F", false, false, guestFood, hostFood, hobbies, hobbies);

        Person person1 = new Person("Dupont", "Jean", LocalDate.of(1985, 3, 15), Pays.FRANCE, true, crit);
        Person person2 = new Person("Martin", "Alice", LocalDate.of(1990, 6, 20), Pays.FRANCE, false, crit);

        System.out.println("Ajout person1: " + plateform.addPerson(person1));
        System.out.println("Ajout person2: " + plateform.addPerson(person2));

        System.out.println("Liste personnes dans la plateforme:");
        for (Person p : plateform.getListPersonnes()) {
            System.out.println("- " + p.getPrenom() + " " + p.getNom());
        }

        Person found = plateform.findPersonByName("Martin");
        System.out.println("Recherche par nom 'Martin': " + (found != null ? found.getPrenom() + " " + found.getNom() : "non trouvé"));

        System.out.println("Suppression person1: " + plateform.removePerson(person1));
        System.out.println("Liste personnes après suppression:");
        for (Person p : plateform.getListPersonnes()) {
            System.out.println("- " + p.getPrenom() + " " + p.getNom());
        }

        System.out.println("Suppression person1 (déjà supprimée): " + plateform.removePerson(person1));
    }
}
