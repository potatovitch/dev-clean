package code;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Guest extends Person{

    HashMap<Critere, String> criteres;

    public Guest(String nom, String prenom, LocalDate dNaiss, Pays pays, ArrayList<String> hobbies, HashMap<Critere, String> criteres, ArrayList<LocalDate> historique) {
        super(nom, prenom, dNaiss, pays, hobbies, historique);
        this.criteres = criteres;
    }
    public Guest(String nom, String prenom, LocalDate dNaiss, Pays pays) {
        super(nom, prenom, dNaiss, pays);
        this.criteres = new HashMap<Critere, String>();
    }


     /**
     * Vérifie si la personne respecte les contraintes redhibitoires
     * le but est d'empêcher les appariements entre personnes qui ne sont stictement pas compatibles
     */
    @Override
    public boolean isCompatible(Person p) {
        if (p.getClass() == Guest.class) {
            return false; // ne pas apparier deux guests
        }
        for (Critere c : criteres.keySet()) {
            switch (c.name()){
                case "GUEST_HAS_ANIMAL":
                    if (p.getCriteres().containsKey(Critere.HOST_HAS_ANIMAL) &&
                        p.getCriteres().get(Critere.HOST_HAS_ANIMAL).equals("true")){
                        return false;
                    }
                case "HISTORY":
                    if (p.getCriteres().containsKey(Critere.HISTORY) &&
                        p.getCriteres().get(Critere.HISTORY).equals(this.criteres.get(Critere.HISTORY))){
                            for (LocalDate date : p.getHistorique()) {
                                if (this.getHistorique().contains(date)){
                                    break;
                                }
                            }
                        return false;
                    }
            }
        }
        return true;
    }
    

     /**
     * Calcul l'affinité entre deux personnes ----->  TODO: mettre les variables des poids a par + rajouter la gestion de l'écart d'age
     * le but est de calculer l'affinité entre deux personnes pour les apparier ensuite de manière optimale
     */
    @Override
    public int calculerAffinite(Person p){
        int affinite = 0;
        for (Critere c : criteres.keySet()) {
            switch (c.name()){
                case "GENDER":
                    if (p.getCriteres().containsKey(Critere.PAIR_GENDER) &&
                        !p.getCriteres().get(Critere.PAIR_GENDER).equals(p.getCriteres().get(Critere.GENDER))){
                        affinite += 20; // Pénalité pour le mauvais genre
                    }
                case "GUEST_FOOD":
                    if (p.getCriteres().containsKey(Critere.HOST_FOOD) &&
                        !p.getCriteres().get(Critere.HOST_FOOD).equals(p.getCriteres().get(Critere.GUEST_FOOD))){
                        affinite += 15; // Pénalité pour la mauvaise nourriture
                    }
                case "HAS_HOBBIES":
                    if (this.getCriteres().get(c).equals("true") &&                     // a des hobbies
                        p.getCriteres().containsKey(Critere.HAS_HOBBIES) &&
                        p.getCriteres().get(Critere.HAS_HOBBIES).equals("true") ){      // a des hobbies
                        boolean sameHobby = false;
                        for (String hobby : this.getHobbies()) {
                            if (p.getHobbies().contains(hobby)) {
                                sameHobby = true;
                                break;      // casse la boucle des qu'on trouve un hobby en commun
                            }
                        }
                        if (!sameHobby){
                            affinite += 10; // Pénalité pour les mauvais hobbies
                        }
                    }
            }
        }
        return affinite;
    }

    @Override
    public HashMap<Critere, String> getCriteres() {
        return criteres;
    }
}
