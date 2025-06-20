package code;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

// stocke les personnes et les paires
public class Plateform {
    private static ArrayList<Person> listPersonnes;
    private static ArrayList<Pair> listPairs;

    public Plateform() {
        Plateform.listPersonnes = new ArrayList<Person>();
        Plateform.listPairs = new ArrayList<Pair>();
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


    ///// sauvegarde et chargement de la plateforme (à revoir j'ai pas checké + pas de csv ici) /////
    ///// ça va probablement degager /////

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
            oos.writeObject(Plateform.listPersonnes);
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde des personnes : " + e.getMessage());
        }
    }

    public void savePairs(File file) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(Plateform.listPairs);
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde des paires : " + e.getMessage());
        }
    }

    ///// sauvegarde et chargement de la plateforme (à revoir j'ai pas checké) /////



    public boolean addPerson(Person p) {
        return Plateform.listPersonnes.add(p);
    }

    public boolean addPair(Pair p) {
        return Plateform.listPairs.add(p);
    }

    public boolean removePerson(Person p) {
        return Plateform.listPersonnes.remove(p);
    }

    public boolean removePair(Pair p) {
        return Plateform.listPairs.remove(p);
    }

    public ArrayList<Person> getListPersonnes() {
        return Plateform.listPersonnes;
    }

    public static ArrayList<Person> getListPersonnesStatic() {
        return Plateform.listPersonnes;
    }

    public Pair[] getListPairs() {
        Pair[] pairsArray = new Pair[Plateform.listPairs.size()];
        return Plateform.listPairs.toArray(pairsArray);
    }

    public static ArrayList<Pair> getListPairsStatic() {
        return Plateform.listPairs;
    }

    public static Person findPersonByNameStatic(String nom1) {
        for (Person p : Plateform.listPersonnes) {
            if (p.getNom().equals(nom1)) {
                return p;
            }
        }
        return null;
    }

}
