package code;

import java.time.LocalDate;
public abstract class Person {
    private String nom;
    private String prenom;
    private LocalDate dNaiss;
    private Pays pays;

    protected boolean isHost;            // initialisé dans Host ou Guest
    protected Critere critere;           // initialisé dans Host ou Guest

    public Person(String nom, String prenom, LocalDate dNaiss, Pays pays) {
        this.nom = nom;
        this.prenom = prenom;
        this.dNaiss = dNaiss;
        this.pays = pays;
    }

    /*
     * donne l'écart d'age sous forme de date (un peu brouillon dans la logique)
     */
    public LocalDate calculerEcartAge(Person p) {
        if (this.dNaiss.compareTo(p.dNaiss) > 0){
            return this.dNaiss.minusYears(p.dNaiss.getYear()).minusMonths(p.dNaiss.getMonthValue()).minusDays(p.dNaiss.getDayOfMonth());
        }else if (this.dNaiss.compareTo(p.dNaiss) < 0){
            return p.dNaiss.minusYears(this.dNaiss.getYear()).minusMonths(this.dNaiss.getMonthValue()).minusDays(this.dNaiss.getDayOfMonth());
        }else{
            return LocalDate.of(0, 0, 0);
        }
    }

    public boolean isEcartTropGrand(Person p){
        // 1 an et demi et un jour
        return LocalDate.of(1, 6, 1).compareTo(dNaiss) < 0;
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


}