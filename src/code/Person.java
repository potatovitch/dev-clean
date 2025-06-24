package code;

import java.time.LocalDate;
import java.time.Period;

public abstract class Person {
    private String nom;
    private String prenom;
    private LocalDate dNaiss;
    private Pays pays;

    public boolean isHost;            // initialisé dans Host ou Guest
    protected Critere critere;           // initialisé dans Host ou Guest

    public Person(String nom, String prenom, LocalDate dNaiss, Pays pays) {
        this.nom = nom;
        this.prenom = prenom;
        this.dNaiss = dNaiss;
        this.pays = pays;
    }

    /**
     * Calcule l'écart d'âge en années entre deux personnes
     */
    public int calculerEcartAge(Person p) {
        Period period = Period.between(this.dNaiss, p.dNaiss);
        return Math.abs(period.getYears());
    }

    /**
     * Vérifie si l'écart d'âge est trop important (plus de 2 ans)
     */
    public boolean isEcartTropGrand(Person p) {
        return calculerEcartAge(p) > 2;
    }

    public abstract int calculerAffinite(Person p);
    public abstract boolean isCompatible(Person p);
    public abstract Critere getCriteres();

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

    // Setters pour permettre la modification
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDNaiss(LocalDate dNaiss) {
        this.dNaiss = dNaiss;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }
}