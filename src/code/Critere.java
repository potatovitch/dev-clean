package code;

import java.util.ArrayList;

public class Critere {
    protected String gender;
    protected String pairGender;
    protected boolean hasAllergy;
    protected boolean hasAnimal;

    protected ArrayList<String> listGuestFoodConstraint;
    protected ArrayList<String> listHostFood;
    protected ArrayList<String> listHobbies;
    protected ArrayList<String> historique;

    public Critere(String gender, String pairGender, boolean hasAllergy, boolean hasAnimal,
                    ArrayList<String> listGuestFoodConstraint, ArrayList<String> listHostFood,
                    ArrayList<String> listHobbies, ArrayList<String> historique) {
        this.gender = gender;
        this.pairGender = pairGender;
        this.hasAllergy = hasAllergy;
        this.hasAnimal = hasAnimal;
        
        this.listGuestFoodConstraint = listGuestFoodConstraint;
        this.listHostFood = listHostFood;
        this.listHobbies = listHobbies != null ? listHobbies : new ArrayList<>();
        this.historique = historique != null ? historique : new ArrayList<>();
    }

    public String toStringGuestFoodConstraint() {
        String ch = "";
        for (String food : listGuestFoodConstraint) {
            ch += food + ',';
        }
        return trimList(ch);
    }

    public String toStringHostFood() {
        String ch = "";
        for (String food : listHostFood) {
            ch += food + ',';
        }
        return trimList(ch);
    }

    public String toStringHobbies() {
        String ch = "";
        for (String food : listHobbies) {
            ch += food + ',';
        }
        return trimList(ch);
    }

    public String toStringHistory() {
        String ch = "";
        for (String food : historique) {
            ch += food + ',';
        }
        return trimList(ch);
    }

    public static String trimList(String ch){
        return ch.substring(0, ch.length()-1);
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
    
    public ArrayList<String> getHistorique() {
        return historique;
    }

    public ArrayList<String> getListHobbies() {
        return listHobbies;
    }

    public String getGender() {
        return gender;
    }

    public String getPairGender() {
        return pairGender;
    }
    
}