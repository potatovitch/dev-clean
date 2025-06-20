import java.util.ArrayList;

// stocke les personnes et les paires
public class Plateform {
    private ArrayList<Person> listPersonnes;
    private ArrayList<Pair> listPairs;

    public Plateform() {
        this.listPersonnes = new ArrayList<Person>();
        this.listPairs = new ArrayList<Pair>();
    }

    public boolean addPerson(Person p) {
        return this.listPersonnes.add(p);
    }

    public boolean addPair(Pair p) {
        return this.listPairs.add(p);
    }

    public boolean removePerson(Person p) {
        return this.listPersonnes.remove(p);
    }

    public boolean removePair(Pair p) {
        return this.listPairs.remove(p);
    }

    public void removeWorstPairs(){
        // TODO: implement
    }
}
