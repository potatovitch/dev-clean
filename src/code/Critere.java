package code;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Critere {
    private ArrayList<String> listHobbies;
    private ArrayList<LocalDate> historique;
    private Gender gender;
    private Gender pairGender;

    public Critere(Gender gender, Gender paiGender, ArrayList<String> listHobbies, ArrayList<LocalDate> historique) {
        this.listHobbies = listHobbies;
        this.historique = historique;
        this.gender = gender;
        this.pairGender = paiGender;
    }

    public Critere(Gender gender, Gender paiGender) {
        this(gender, paiGender, new ArrayList<String>() , new ArrayList<LocalDate>());
    }

    public void addHobby(String hobby) {
        if (!this.listHobbies.contains(hobby)) {
            this.listHobbies.add(hobby);
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

    public boolean isGoodPairingGender(Critere c){
        return this.pairGender.equals(c.gender) && this.gender.equals(c.pairGender);
    }

    public boolean isGoodPairingHobbies(Critere c){
        for (String hobby : this.getHobbies()) {
            if (c.getHobbies().contains(hobby)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getHobbies() {
        return listHobbies;
    }
    public ArrayList<LocalDate> getHistorique() {
        return historique;
    }

    public ArrayList<String> getListHobbies() {
        return listHobbies;
    }

    public Gender getGender() {
        return gender;
    }

    public Gender getPairGender() {
        return pairGender;
    }

    public abstract boolean getAnimal();
    public abstract String toStringFood();

    
}