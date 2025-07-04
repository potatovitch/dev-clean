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
        if (person1.isCompatible(person2) && !pair.containsKey(person1) && !pair.containsValue(person2)){
            if (person1.isHost()){
                this.pair.put(person1, person2);
            }else{
                this.pair.put(person2, person1);
            }
        }
    }

    public static void put(HashMap<Person, Person> pair, Person person1, Person person2) throws IllegalArgumentException {
        if (person1 == null || person2 == null) {
            throw new IllegalArgumentException("une personne ne peut pas être nulle");
        }
        if (person1.isCompatible(person2) && !pair.containsKey(person1) && !pair.containsValue(person2)){
            pair.put(person1, person2);
        }
    }

    public boolean isCompatible(Person p1, Person p2) {
        return p1.isCompatible(p2);
    }

    public int calculerAffinite(Person p1, Person p2) {
        return p1.calculerAffinite(p2);
    }

    public Person getPerson1() {
        return (Person) pair.keySet().toArray()[0];
    }

    public Person getPerson2() {
        return pair.get(getPerson1());
    }

}
