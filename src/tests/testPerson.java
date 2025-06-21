package tests;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import code.Critere;
import code.Guest;
import code.Hobby;
import code.Host;
import code.Pays;
import code.Person;

public class testPerson {
    public static void main(String[] args) {
        Person host = new Host("Doe", "John", LocalDate.of(1990, 1, 1), Pays.FRANCE);
        Person guest = new Guest("Smith", "Jane", LocalDate.of(1992, 2, 2), Pays.ALLEMAGNE);

        HashMap<Critere, String> criteresHost = new HashMap<Critere, String>();
        criteresHost.put(Critere.HOST_HAS_ANIMAL, "true");
        criteresHost.put(Critere.HOST_FOOD, "FRANCE");
        criteresHost.put(Critere.HAS_HISTORY, "true");
        criteresHost.put(Critere.PAIR_GENDER, "male");
        HashMap<Critere, String> criteresGuest = new HashMap<Critere, String>();
        criteresGuest.put(Critere.HOST_HAS_ANIMAL, "false");
        criteresGuest.put(Critere.GENDER, "female");

        ArrayList<String> hobbiesHost = new ArrayList<String>();
        hobbiesHost.add(Hobby.HOBBY1.getLabel());
        hobbiesHost.add(Hobby.HOBBY2.getLabel());
        ArrayList<String> hobbiesGuest = new ArrayList<String>();
        hobbiesGuest.add(Hobby.HOBBY1.getLabel());
        hobbiesGuest.add(Hobby.HOBBY3.getLabel());

        
    }
}
