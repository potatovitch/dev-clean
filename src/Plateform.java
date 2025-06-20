import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

// stocke les personnes et les paires
public class Plateform {
    private ArrayList<Person> listPersonnes;
    private ArrayList<Pair> listPairs;

    public Plateform() {
        this.listPersonnes = new ArrayList<Person>();
        this.listPairs = new ArrayList<Pair>();
        try {
            listPersonnes = loadPersons(null);
        } catch (Exception e) {
            e.getMessage();
        }
        try {
            listPairs = loadPairs(null);
        } catch (Exception e) {
            e.getMessage();
        }
    }


    ///// sauvegarde et chargement de la plateforme (à revoir j'ai pas checké) /////

    public static ArrayList<Person> loadPersons(File file) {
        ArrayList<Person> persons = new ArrayList<Person>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            persons = (ArrayList<Person>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.getMessage();
        }
        return persons;
    }

    public static ArrayList<Pair> loadPairs(File file) {
        ArrayList<Pair> pairs = new ArrayList<Pair>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            pairs = (ArrayList<Pair>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.getMessage();
        }
        return pairs;
    }


    public void savePersons(File file) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(this.listPersonnes);
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde des personnes : " + e.getMessage());
        }
    }

    public void savePairs(File file) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(this.listPairs);
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde des paires : " + e.getMessage());
        }
    }

    ///// sauvegarde et chargement de la plateforme (à revoir j'ai pas checké) /////



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

    public ArrayList<Person> getListPersonnes() {
        return listPersonnes;
    }

    public Pair[] getListPairs() {
        Pair[] pairsArray = new Pair[listPairs.size()];
        return listPairs.toArray(pairsArray);
    }
}
