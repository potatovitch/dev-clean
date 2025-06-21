package code;

import java.time.LocalDate;
import java.util.ArrayList;

public class Guest extends Person{

    public Guest(String nom, String prenom, LocalDate dNaiss, Pays pays, Gender gender, Gender pairGender, boolean hasAllergy, ArrayList<String> listGuestFood) {
        super(nom, prenom, dNaiss, pays);
        super.isHost = false;
        super.critere = new GuestCritere(gender, pairGender, hasAllergy, listGuestFood);
    }

     /**
     * Vérifie si la personne respecte les contraintes redhibitoires
     * le but est d'empêcher les appariements entre personnes qui ne sont stictement pas compatibles
     */
    @Override    
    public boolean isCompatible(Person p) {
        if (p.getClass() == Host.class){
            Host temp = (Host)p;
            return temp.isCompatible(this);
        }
        return false;
    }
    

     /**
     * Calcul l'affinité entre deux personnes ----->  TODO : mettre les variables des poids a par + rajouter la gestion de l'écart d'age
     * le but est de calculer l'affinité entre deux personnes pour les apparier ensuite de manière optimale
     */
    @Override     
    public int calculerAffinite(Person p){
        return p.calculerAffinite(this);    
    }

    @Override
    public Critere getCriteres() {
        return critere;
    }
}
