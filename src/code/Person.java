package code;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;

public class Person {
    private static HashMap<String, String> infoPersonnes = new HashMap<String, String>();

    private String nom;
    private String prenom;
    private LocalDate dNaiss;
    private Pays pays;
    public boolean isHost;

    protected Critere critere;

    public Person(String nom, String prenom, LocalDate dNaiss, Pays pays, boolean isHost, Critere crit) {
        this.nom = nom;
        this.prenom = prenom;
        this.dNaiss = dNaiss;
        this.pays = pays;
        this.isHost = isHost;
        this.critere = crit;

        infoPersonnes.put(null, "T");        // nom 
        infoPersonnes.put(null, "T");        // prenom
        infoPersonnes.put(null, "D");        // dnaiss
        infoPersonnes.put(null, "T");        // pays
        infoPersonnes.put(null, "B");        // isHost
        infoPersonnes.put(null, "T");        // gender
        infoPersonnes.put(null, "T");        // pairGender
        infoPersonnes.put(null, "B");        // isAlergique
        infoPersonnes.put(null, "B");        // hasAnimal
        infoPersonnes.put(null, "T");        // guestfood
        infoPersonnes.put(null, "T");        // hostfood
        infoPersonnes.put(null, "T");        // hobbies
        infoPersonnes.put(null, "T");        // history
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

    public void updateInfo(){
        HashMap<String, String> temp = new HashMap<String, String>();

        temp.put(nom , "T");
        temp.put(prenom , "T");
        temp.put(String.valueOf(dNaiss) , "D");
        temp.put(String.valueOf(pays) , "T");
        temp.put(toStringBool(isHost) , "B");
        temp.put(critere.gender , "T");
        temp.put(critere.pairGender , "T");
        temp.put(toStringBool(critere.hasAllergy) , "B");
        temp.put(toStringBool(critere.hasAnimal) , "B");
        temp.put(critere.toStringGuestFoodConstraint() , "T");
        temp.put(critere.toStringHostFood() , "T");
        temp.put(critere.toStringHobbies() , "T");
        temp.put(critere.toStringHistory() , "T");
    }

    public String toStringBool(boolean b){
        if (b){
            return "yes";
        }return "no";
    }

    public boolean isCompatible(Person p){
        if (this.isHost == p.isHost){
            return false;
        }
        if (this.critere.hasAnimal != p.critere.hasAllergy ){
            return false;
        }
        if (this.isHost){
            for (String food : this.critere.listHostFood) {
                if (p.critere.toStringGuestFoodConstraint().contains(food)){
                    return true;
                }
            }
            return false;
        }else{
            for (String food : p.critere.listHostFood) {
                if (this.critere.toStringGuestFoodConstraint().contains(food)){
                    return true;
                }
            }
            return false;
        }
    }

    public int calculerAffinite(Person p){
        int affinite = 0;

        if (!this.critere.pairGender.equals(p.critere.gender) ||
            !p.critere.pairGender.equals(this.critere.gender)){
            return affinite += 20;
        }

        boolean compatibleHobbies = false;
        for (String hobby : this.critere.listHobbies) {
            if (p.critere.toStringHobbies().contains(hobby)){
                compatibleHobbies = true;
                break;
            }
        }
        if (!compatibleHobbies){
            affinite += 15;
        }

        if (this.isEcartTropGrand(p)){
            affinite += 10;
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

    public boolean isHost() {
        return this.isHost;
    }

}