package tests;

import code.Pair;
import code.Person;
import code.Critere;
import code.Pays;

import java.time.LocalDate;
import java.util.ArrayList;

public class PairTest {

    public static void main(String[] args) {
        ArrayList<String> guestFood = new ArrayList<>();
        guestFood.add("No gluten");

        ArrayList<String> hostFood = new ArrayList<>();
        hostFood.add("Vegetarian");

        ArrayList<String> hobbies = new ArrayList<>();
        hobbies.add("Reading");

        Critere crit1 = new Critere("M", "F", false, false, guestFood, hostFood, hobbies, hobbies);
        Critere crit2 = new Critere("F", "M", false, false, guestFood, hostFood, hobbies, hobbies);

        Person person1 = new Person("Dupont", "Jean", LocalDate.of(1985, 3, 15), Pays.FRANCE, true, crit1);
        Person person2 = new Person("Martin", "Alice", LocalDate.of(1990, 6, 20), Pays.FRANCE, false, crit2);

        Pair pair = new Pair(person1, person2);

        System.out.println("Personne 1 : " + pair.getPerson1().getPrenom() + " " + pair.getPerson1().getNom());
        System.out.println("Personne 2 : " + pair.getPerson2().getPrenom() + " " + pair.getPerson2().getNom());

        boolean compatible = pair.isCompatible(person1, person2);
        System.out.println("Sont-ils compatibles ? " + compatible);

        int affinite = pair.calculerAffinite(person1, person2);
        System.out.println("Affinité calculée : " + affinite);

        try {
            pair.put(person1, person2);
            System.out.println("Ajout réussi");
        } catch (IllegalArgumentException e) {
            System.out.println("Exception lors de l'ajout : " + e.getMessage());
        }

        try {
            pair.put(null, person2);
            System.out.println("Ajout d'une personne null réussi");
        } catch (IllegalArgumentException e) {
            System.out.println("Exception attendue pour ajout null: " + e.getMessage());
        }
    }
}
