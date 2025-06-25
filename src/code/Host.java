package code;

import java.time.LocalDate;
import java.util.ArrayList;

import appInterface.Controller;

public class Host extends Person{
    
    public Host(String nom, String prenom, LocalDate dNaiss, Pays pays, Gender gender, Gender pairGender, boolean hasAnimal, ArrayList<Food> listHostFood) {
        super(nom, prenom, dNaiss, pays);
        super.isHost = true;
        super.critere = new HostCritere(gender, pairGender, hasAnimal, listHostFood);

    }

     /**
     * Vérifie si la personne respecte les contraintes redhibitoires
     * le but est d'empêcher les appariements entre personnes qui ne sont stictement pas compatibles
     */
    @Override     
    public boolean isCompatible(Person p) {
        if (p.getClass() == Host.class) {
            return false; // ne pas apparier deux guests
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
     * Calcul l'affinité entre deux personnes
     * le but est de calculer l'affinité entre deux personnes pour les apparier ensuite de manière optimale
     */
    @Override 
    public int calculerAffinite(Person p){
        int affinite = 0;
        HostCritere crit = (HostCritere)this.getCriteres();
        GuestCritere pCrit = (GuestCritere)p.getCriteres();
        if (!crit.isGoodPairingGender(pCrit)){
            affinite += Controller.getPoidsGender();
        }
        if (!crit.isGoodPairingHobbies(pCrit)){
            affinite += Controller.getPoidsHobby();
        }
        if (this.isEcartTropGrand(p)){
            affinite += Controller.getPoidsAge();
        }
        
        return affinite;
    }

	 @Override
	 public Critere getCriteres() {
		return critere;
	 }

}



