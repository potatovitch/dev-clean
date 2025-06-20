package code;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Person {
    private String nom;
    private String prenom;
    private LocalDate dNaiss;
    private Pays pays;
    private Map<Critere, String> criteres;
    private ArrayList<String> hobbies;

    public Person(String nom, String prenom, LocalDate dNaiss, Pays pays) {
        this.nom = nom;
        this.prenom = prenom;
        this.dNaiss = dNaiss;
        this.pays = pays;
        this.criteres = new HashMap<Critere, String>();
        this.hobbies = new ArrayList<String>();
    }

    public boolean addCritere(Critere c) {
        return this.criteres.put(c, "" + c.getType()) != null;
    }

    public void addHobby(String hobby) {
        if (!this.hobbies.contains(hobby)) {
            this.hobbies.add(hobby);
        }
    }

    public void addHobby(ArrayList<String> hobbies) {
        for (String hobby : hobbies) {
            addHobby(hobby);
        }
    }

    /**
     * Vérifie si la personne respecte les contraintes redhibitoires
     */
    public boolean isCompatible(Person p){
        for (Critere c : criteres.keySet()) {
            switch (c.name()){
                case "GUEST_ANIMAL_ALLERGY":
                    if (p.criteres.containsKey(Critere.HOST_HAS_ANIMAL) &&
                        p.criteres.get(Critere.HOST_HAS_ANIMAL).equals("true")){
                        return false;
                    }
                case "HISTORY":
                    if (p.criteres.containsKey(Critere.HISTORY) &&
                        p.criteres.get(Critere.HISTORY).equals(this.criteres.get(Critere.HISTORY))){
                        return false;
                    }
            }
        }
        return true;
    }


    /**
     * Calcul l'affinité entre deux personnes ----->  TODO: mettre les variables des poids a par
     */
    public int calculerAffinite(Person p){
        int affinite = 0;
        for (Critere c : criteres.keySet()) {
            switch (c.name()){
                case "GENDER":
                    if (p.criteres.containsKey(Critere.PAIR_GENDER) &&
                        p.criteres.get(Critere.PAIR_GENDER).equals(p.criteres.get(Critere.GENDER))){
                        affinite += 20; // Pénalité pour allergie à l'animal
                    }
                case "GUEST_FOOD":
                    if (this.criteres.containsKey(Critere.HOST_FOOD) &&
                        !this.criteres.get(Critere.HOST_FOOD).equals(p.criteres.get(Critere.GUEST_FOOD))){
                        affinite += 15; // Pénalité pour animal chez l'hôte
                    }
                case "HOBBIES":
                    if (this.criteres.containsKey(Critere.HOBBIES) &&
                        this.criteres.get(Critere.HOBBIES).equals("yes") ){

                        for (String hobby : this.hobbies) {
                            if (!p.hobbies.contains(hobby)) {
                                break;
                            }
                            affinite += 10; // Pénalité pour animal chez l'hôte
                        }
                    }
            }
        }
        return affinite;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public LocalDate getdNaiss() {
        return dNaiss;
    }

    public Pays getPays() {
        return pays;
    }

    public Map<Critere, String> getCriteres() {
        return criteres;
    }

    public ArrayList<String> getHobbies() {
        return hobbies;
    }

}