package tests;

import code.Person;
import code.Critere;
import code.Pays;

import java.time.LocalDate;
import java.util.ArrayList;

public class PersonTest {

    public static void main(String[] args) {
        ArrayList<String> guestFood1 = new ArrayList<>();
        guestFood1.add("No gluten");

        ArrayList<String> hostFood1 = new ArrayList<>();
        hostFood1.add("Vegetarian");

        ArrayList<String> hobbies1 = new ArrayList<>();
        hobbies1.add("Reading");

        Critere crit1 = new Critere("M", "F", false, false, guestFood1, hostFood1, hobbies1, hobbies1);

        ArrayList<String> guestFood2 = new ArrayList<>();
        guestFood2.add("Vegetarian");

        ArrayList<String> hostFood2 = new ArrayList<>();
        hostFood2.add("No gluten");

        ArrayList<String> hobbies2 = new ArrayList<>();
        hobbies2.add("Sports");

        Critere crit2 = new Critere("F", "M", false, false, guestFood2, hostFood2, hobbies2, hobbies2);

        Person person1 = new Person("Dupont", "Jean", LocalDate.of(1985, 3, 15), Pays.FRANCE, true, crit1);
        Person person2 = new Person("Martin", "Alice", LocalDate.of(1990, 6, 20), Pays.FRANCE, false, crit2);

        System.out.println("Person1 est host ? " + person1.isHost());
        System.out.println("Person2 est host ? " + person2.isHost());
        System.out.println("Sont-ils compatibles ? " + person1.isCompatible(person2));

        int affinite = person1.calculerAffinite(person2);
        System.out.println("Affinité entre Person1 et Person2 : " + affinite);

        int ecartAge = person1.calculerEcartAge(person2);
        System.out.println("Écart d'âge : " + ecartAge);

        boolean ecartTropGrand = person1.isEcartTropGrand(person2);
        System.out.println("L'écart d'âge est-il trop grand ? " + ecartTropGrand);

        System.out.println("Infos Person1 avant modif : " + person1.getNom() + ", " + person1.getPrenom());
        person1.setNom("Durand");
        person1.setPrenom("Paul");
        person1.updateInfo();
        System.out.println("Infos Person1 après modif : " + person1.getNom() + ", " + person1.getPrenom());
    }
}
