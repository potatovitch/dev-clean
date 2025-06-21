package code;

import java.util.ArrayList;

// stocke les personnes et les paires
// les changements concernant les etudiants et les appariements sont faits ici, puis seront ensuite utilisés dans Controller pour l'affichage dans l'interface
public class Plateform {
    private static ArrayList<Person> listPersonnes;
    private static ArrayList<Pair> listPairs;

    public Plateform() {
        Plateform.listPersonnes = new ArrayList<Person>();
        Plateform.listPairs = new ArrayList<Pair>();
        try {
            loadFromCSV();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void loadFromCSV() {
       loadPairsFromCSV("paires.csv");
       loadPersonsFromCSV("personnes.csv");
    }

    public ArrayList<Person> loadPersonsFromCSV(String filePath) {
        return CSVHandler.loadPersonsFromCSV(filePath);
    }
    public ArrayList<Pair> loadPairsFromCSV(String filePath) {
        return CSVHandler.loadPairsFromCSV(filePath);
    }

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

    
    public void appariementOptimise(){
        // TODO : a faire   appariementOptimisé (truc de graphe avec les matrices)

        // genre tu fait le calcul de la matrice avec deux clones de listPersonne et tu stocke le resultat dans une matrice de int[listPersonne.size()]

        /*
         * le truc comme ça de graphe
         * 
         *  ( , , , , , )
         *  ( , , , , , )
         *  ( , , , , , )
         *  ( , , , , , )
         *  ( , , , , , )
         */
    }
}
