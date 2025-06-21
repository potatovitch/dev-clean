package code;

import java.time.LocalDate;
import java.util.ArrayList;

public class Host extends Person{
    
    public Host(String nom, String prenom, LocalDate dNaiss, Pays pays, Gender gender, Gender pairGender, boolean hasAnimal, ArrayList<String> listHostFood) {
        super(nom, prenom, dNaiss, pays);
        super.isHost = true;
        super.critere = new HostCritere(gender, pairGender, hasAnimal, listHostFood);

    }

     /**
     * Vérifie si la personne respecte les contraintes redhibitoires
     * le but est d'empêcher les appariements entre personnes qui ne sont stictement pas compatibles
     */
    @Override       // TODO : a faire + voir si pas move dans Critere
    public boolean isCompatible(Person p) {
        if (p.getClass() == Host.class) {
            return false; // ne pas apparier deux guests
        }
        if (this.isEcartTropGrand(p)){
            return false;
        }
        HostCritere crit = (HostCritere)this.getCriteres();
        GuestCritere pCrit = (GuestCritere)p.getCriteres();
        if (crit.aUnAnimal() && pCrit.estAllergique()){
            return false;   // allergique 
        }
        if (!crit.getHistorique().isEmpty() && !pCrit.getHistorique().isEmpty()){
            for (LocalDate date : crit.getHistorique()) {
                if (pCrit.getHistorique().contains(date)){
                    return false;   // ce sont deja rencontrés
                }
            }
        }
        
        return true;
    }
    

     /**
     * Calcul l'affinité entre deux personnes ----->  TODO : mettre les variables des poids a par + rajouter la gestion de l'écart d'age
     * le but est de calculer l'affinité entre deux personnes pour les apparier ensuite de manière optimale
     */
    @Override 
    public int calculerAffinite(Person p){
        int affinite = 0;
        HostCritere crit = (HostCritere)this.getCriteres();
        GuestCritere pCrit = (GuestCritere)p.getCriteres();
        if (!crit.isGoodPairingGender(pCrit)){
            affinite += 20;
        }
        if (!crit.isGoodPairingHobbies(pCrit)){
            affinite += 15;
        }
        if (!crit.isGoodFood(pCrit)){
            affinite += 10;
        }
        
        return affinite;
    }

    @Override
    public Critere getCriteres() {
        return critere;
    }
}



