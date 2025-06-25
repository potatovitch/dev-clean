package code;

import java.time.LocalDate;
import java.util.ArrayList;
import appInterface.MainController;

public class Host extends Person{
    
    public Host(String nom, String prenom, LocalDate dNaiss, Pays pays, Gender gender, Gender pairGender, boolean hasAnimal, ArrayList<String> listHostFood) {
        super(nom, prenom, dNaiss, pays);
        super.isHost = true;
        super.critere = new HostCritere(gender, pairGender, hasAnimal, listHostFood);
    }

    /**
     * Vérifie si la personne respecte les contraintes redhibitoires
     * le but est d'empêcher les appariements entre personnes qui ne sont strictement pas compatibles
     */
    @Override     
    public boolean isCompatible(Person p) {
        if (p.getClass() == Host.class) {
            return false; // ne pas apparier deux hosts
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
                    return false;   // se sont déjà rencontrés
                }
            }
        }
        
        return true;
    }
    
    /**
     * Calcule l'affinité entre deux personnes (plus le score est élevé, plus l'affinité est bonne)
     * le but est de calculer l'affinité entre deux personnes pour les apparier ensuite de manière optimale
     */
    @Override 
    public int calculerAffinite(Person p){
        int affinite = 0;
        HostCritere crit = (HostCritere)this.getCriteres();
        GuestCritere pCrit = (GuestCritere)p.getCriteres();
        
        if (crit.isGoodPairingGender(pCrit)){
            affinite += (int)MainController.getPoidsGender();
        }
        
        if (crit.isGoodPairingHobbies(pCrit)){
            affinite += (int)MainController.getPoidsHobby();
        }
        
        if (crit.isGoodFood(pCrit)){
            affinite += (int)MainController.getPoidsFood();
        }
        
        int ecartAge = this.calculerEcartAge(p);
        if (ecartAge == 0) {
            affinite += (int)MainController.getPoidsAge();
        } else if (ecartAge == 1) {
            affinite += (int)(MainController.getPoidsAge() * 0.5);
        }
        
        return affinite;
    }

    @Override
    public Critere getCriteres() {
        return critere;
    }
}