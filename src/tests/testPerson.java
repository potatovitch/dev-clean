package tests;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import code.Critere;
import code.Guest;
import code.Host;
import code.Pays;
import code.Person;

public class testPerson {
    public static void main(String[] args) {
        Person host = new Host("Doe", "John", LocalDate.of(1990, 1, 1), Pays.FRANCE);
        Person guest = new Guest("Smith", "Jane", LocalDate.of(1992, 2, 2), Pays.ALLEMAGNE);

        HashMap<Critere, String> criteres1 = new HashMap<Critere, String>();
        criteres1.put(Critere.HOST_HAS_ANIMAL, "true");
        criteres1.put(Critere.HISTORY, "newcomer");
        criteres1.put(Critere.PAIR_GENDER, "male");
        HashMap<Critere, String> criteres2 = new HashMap<Critere, String>();
        criteres2.put(Critere.HOST_HAS_ANIMAL, "false");
        criteres2.put(Critere.GENDER, "female");

        ArrayList<String> hobbies1 = new ArrayList<String>();
        ArrayList<String> hobbies2 = new ArrayList<String>();


    }
}
