package code;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Person {
    private String nom;
    private String prenom;
    private LocalDate dNaiss;
    private Pays pays;

    private ArrayList<String> hobbies;
    private ArrayList<LocalDate> historique;


    public Person(String nom, String prenom, LocalDate dNaiss, Pays pays, ArrayList<String> hobbies, ArrayList<LocalDate> historique) {
        this.nom = nom;
        this.prenom = prenom;
        this.dNaiss = dNaiss;
        this.pays = pays;
        this.hobbies = hobbies;
        this.historique = historique;
    }

    public Person(String nom, String prenom, LocalDate dNaiss, Pays pays) {
        this(nom, prenom, dNaiss, pays, new ArrayList<String>(),new ArrayList<LocalDate>());
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

    public void addHistory(LocalDate date){
        historique.add(date);
    }

    public abstract int calculerAffinite(Person p);
    public abstract boolean isCompatible(Person p);
    public abstract HashMap<Critere, String> getCriteres();

    // Getters
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

    public ArrayList<String> getHobbies() {
        return hobbies;
    }
    public ArrayList<LocalDate> getHistorique() {
        return historique;
    }

}