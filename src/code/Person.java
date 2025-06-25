package code;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;

public class Person {
    private HashMap<String, String> infoPersonnes = new HashMap<>();

    private String nom;
    private String prenom;
    private LocalDate dNaiss;
    private Pays pays;
    private boolean isHost;
    protected Critere critere;

    public Person(String nom, String prenom, LocalDate dNaiss, Pays pays, boolean isHost, Critere crit) {
        this.nom = nom;
        this.prenom = prenom;
        this.dNaiss = dNaiss;
        this.pays = pays;
        this.isHost = isHost;
        this.critere = crit;
        
        updateInfo();
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
        infoPersonnes.clear();

        infoPersonnes.put("nom", nom);
        infoPersonnes.put("prenom", prenom);
        infoPersonnes.put("dateNaissance", dNaiss.toString());
        infoPersonnes.put("pays", pays.toString());
        infoPersonnes.put("isHost", toStringBool(isHost));
        infoPersonnes.put("gender", critere.gender);
        infoPersonnes.put("pairGender", critere.pairGender);
        infoPersonnes.put("hasAllergy", toStringBool(critere.hasAllergy));
        infoPersonnes.put("hasAnimal", toStringBool(critere.hasAnimal));
        infoPersonnes.put("guestFood", critere.toStringGuestFoodConstraint());
        infoPersonnes.put("hostFood", critere.toStringHostFood());
        infoPersonnes.put("hobbies", critere.toStringHobbies());
        infoPersonnes.put("history", critere.toStringHistory());
    }

    public static String toStringBool(boolean b){
        if (b){
            return "yes";
        }
        return "no";
    }

    public static boolean toBoolString(String ch){
        if (ch.equals("yes")){
            return true;
        }return false;
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