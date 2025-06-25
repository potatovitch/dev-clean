package code;

import java.util.ArrayList;
import java.util.HashSet;

public class Plateform {
    private ArrayList<Person> listPersonnes;
    private ArrayList<Pair> listPairs;
    
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
        this.listPairs.clear();
        staticListPairs.clear();

        ArrayList<Person> hosts = new ArrayList<>();
        ArrayList<Person> guests = new ArrayList<>();

        for (Person p : listPersonnes) {
            if (p.isHost) {
                hosts.add(p);
            } else {
                guests.add(p);
            }
        }

        class CandidatePair {
            Person host;
            Person guest;
            int affinite;

            CandidatePair(Person h, Person g, int a) {
                host = h;
                guest = g;
                affinite = a;
            }
        }

        ArrayList<CandidatePair> allPairs = new ArrayList<>();

        for (Person guest : guests) {
            for (Person host : hosts) {
                if (host.isCompatible(guest)) {
                    int aff = host.calculerAffinite(guest);
                    allPairs.add(new CandidatePair(host, guest, aff));
                }
            }
        }

        allPairs.sort((p1, p2) -> Integer.compare(p2.affinite, p1.affinite));

        HashSet<Person> hostsUsed = new HashSet<>();
        HashSet<Person> guestsUsed = new HashSet<>();

        for (CandidatePair cp : allPairs) {
            if (!hostsUsed.contains(cp.host) && !guestsUsed.contains(cp.guest)) {
                Pair nouvellePaire = new Pair(cp.host, cp.guest);
                addPair(nouvellePaire);
                hostsUsed.add(cp.host);
                guestsUsed.add(cp.guest);
            }
        }
    }

}