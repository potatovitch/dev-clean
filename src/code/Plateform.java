package code;

import java.util.ArrayList;

public class Plateform {
    private static ArrayList<Person> listPersonnes = new ArrayList<Person>();
    private static ArrayList<Pair> listPairs = new ArrayList<Pair>();
    

    public Plateform() {
        try {
            loadFromCSV();
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement CSV : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadFromCSV() {
        ArrayList<Pair> loadedPairs = loadPairsFromCSV("paires.csv");
        if (loadedPairs != null) {
            listPairs.clear();
            listPairs.addAll(loadedPairs);
        }

        ArrayList<Person> loadedPersons = loadPersonsFromCSV("personnes.csv");
        if (loadedPersons != null) {
            listPersonnes.clear();
            listPersonnes.addAll(loadedPersons);
        }
    }

    public ArrayList<Person> loadPersonsFromCSV(String filePath) {
        return CSVHandler.loadPersonsFromCSV(filePath);
    }

    public ArrayList<Pair> loadPairsFromCSV(String filePath) {
        return CSVHandler.loadPairsFromCSV(filePath);
    }

    public boolean addPerson(Person p) {
        boolean added = listPersonnes.add(p);
        if (added) {
            listPersonnes.add(p);
        }
        System.out.println("Ajout personne : " + p.getPrenom() + " " + p.getNom());
        return added;
    }

    public boolean addPair(Pair p) {
        boolean added = listPairs.add(p);
        if (added) {
            listPairs.add(p);
        }
        return added;
    }

    public boolean removePerson(Person p) {
        boolean removed = listPersonnes.remove(p);
        if (removed) {
            listPersonnes.remove(p);
        }
        return removed;
    }

    public boolean removePair(Pair p) {
        boolean removed = listPairs.remove(p);
        if (removed) {
            listPairs.remove(p);
        }
        return removed;
    }

    public ArrayList<Person> getListPersonnes() {
        return listPersonnes;
    }

    public ArrayList<Pair> getListPairs() {
        return listPairs;
    }

    // Méthodes statiques requises par CSVHandler
    public static ArrayList<Person> getListPersonnesStatic() {
        return listPersonnes;
    }

    public static ArrayList<Pair> getListPairsStatic() {
        return listPairs;
    }

    public Person findPersonByName(String nom) {
        for (Person p : listPersonnes) {
            if (p.getNom().equals(nom)) {
                return p;
            }
        }
        return null;
    }

    public static Person findPersonByNameStatic(String nom) {
        for (Person p : listPersonnes) {
            if (p.getNom().equals(nom)) {
                return p;
            }
        }
        return null;
    }

    public void appariementOptimise() {
        // Vider la liste des paires existantes
        listPairs.clear();
        listPairs.clear();
        
        ArrayList<Person> hosts = new ArrayList<>();
        ArrayList<Person> guests = new ArrayList<>();
        
        // Séparer les hosts et guests
        for (Person p : listPersonnes) {
            if (p.isHost) {
                hosts.add(p);
            } else {
                guests.add(p);
            }
        }
        
        // Appariement simple : pour chaque guest, trouver le meilleur host compatible
        ArrayList<Person> hostsUtilises = new ArrayList<>();
        
        for (Person guest : guests) {
            Person meilleurHost = null;
            int meilleureAffinite = 999;
            
            for (Person host : hosts) {
                if (!hostsUtilises.contains(host) && host.isCompatible(guest)) {
                    int affinite = host.calculerAffinite(guest);
                    if (affinite < meilleureAffinite) {
                        meilleureAffinite = affinite;
                        meilleurHost = host;
                    }
                }
            }
            
            if (meilleurHost != null) {
                Pair nouvellePaire = new Pair(meilleurHost, guest);
                addPair(nouvellePaire);
                hostsUtilises.add(meilleurHost);
            }
        }
    }


}