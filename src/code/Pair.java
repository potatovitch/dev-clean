package code;

import java.util.HashMap;

public class Pair {
    private HashMap<Person, Person> pair;

    public Pair(Person person1, Person person2) {
        this.pair = new HashMap<Person, Person>(1);
        try {
            put(this.pair, person1, person2);
        } catch (IllegalArgumentException e) {
            e.getMessage();
        }
    }

    public void put(Person person1, Person person2) throws IllegalArgumentException {
        if (person1 == null || person2 == null) {
            throw new IllegalArgumentException("une personne ne peut pas être nulle");
        }
        this.pair.put(person1, person2);
    }

    public static void put(HashMap<Person, Person> pair, Person person1, Person person2) throws IllegalArgumentException {
        if (person1 == null || person2 == null) {
            throw new IllegalArgumentException("une personne ne peut pas être nulle");
        }
        pair.put(person1, person2);
    }

    public boolean isCompatible(Person p1, Person p2) {
        return p1.isCompatible(p2);
    }

    public int calculerAffinite(Person p1, Person p2) {
        return p1.calculerAffinite(p2);
    }

    public Person getPerson1() {
        return (Person) pair.keySet().toArray()[0]; // doit mettre en keyset pour avoir la clé
    }

    public Person getPerson2() {
        return pair.get(getPerson1());
    }

}
