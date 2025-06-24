package code;

import java.util.ArrayList;

public class Plateform {
    private ArrayList<Person> listPersonnes;
    private ArrayList<Pair> listPairs;
    
    // Variables statiques pour les méthodes statiques
    private static ArrayList<Person> staticListPersonnes = new ArrayList<>();
    private static ArrayList<Pair> staticListPairs = new ArrayList<>();

    public Plateform() {
        this.listPersonnes = new ArrayList<>();
        this.listPairs = new ArrayList<>();
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
            this.listPairs.clear();
            this.listPairs.addAll(loadedPairs);
            staticListPairs.clear();
            staticListPairs.addAll(loadedPairs);
        }

        ArrayList<Person> loadedPersons = loadPersonsFromCSV("personnes.csv");
        if (loadedPersons != null) {
            this.listPersonnes.clear();
            this.listPersonnes.addAll(loadedPersons);
            staticListPersonnes.clear();
            staticListPersonnes.addAll(loadedPersons);
        }
    }

    public ArrayList<Person> loadPersonsFromCSV(String filePath) {
        return CSVHandler.loadPersonsFromCSV(filePath);
    }

    public ArrayList<Pair> loadPairsFromCSV(String filePath) {
        return CSVHandler.loadPairsFromCSV(filePath);
    }

    public boolean addPerson(Person p) {
        boolean added = this.listPersonnes.add(p);
        if (added) {
            staticListPersonnes.add(p);
        }
        System.out.println("Ajout personne : " + p.getPrenom() + " " + p.getNom());
        return added;
    }

    public boolean addPair(Pair p) {
        boolean added = this.listPairs.add(p);
        if (added) {
            staticListPairs.add(p);
        }
        return added;
    }

    public boolean removePerson(Person p) {
        boolean removed = this.listPersonnes.remove(p);
        if (removed) {
            staticListPersonnes.remove(p);
        }
        return removed;
    }

    public boolean removePair(Pair p) {
        boolean removed = this.listPairs.remove(p);
        if (removed) {
            staticListPairs.remove(p);
        }
        return removed;
    }

    public ArrayList<Person> getListPersonnes() {
        return this.listPersonnes;
    }

    public ArrayList<Pair> getListPairs() {
        return this.listPairs;
    }

    // Méthodes statiques requises par CSVHandler
    public static ArrayList<Person> getListPersonnesStatic() {
        return staticListPersonnes;
    }

    public static ArrayList<Pair> getListPairsStatic() {
        return staticListPairs;
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
        for (Person p : staticListPersonnes) {
            if (p.getNom().equals(nom)) {
                return p;
            }
        }
        return null;
    }

    public void appariementOptimise() {
        // Vider la liste des paires existantes
        this.listPairs.clear();
        staticListPairs.clear();
        
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
            int meilleureAffinite = -1;
            
            for (Person host : hosts) {
                if (!hostsUtilises.contains(host) && host.isCompatible(guest)) {
                    int affinite = host.calculerAffinite(guest);
                    if (affinite > meilleureAffinite) {
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