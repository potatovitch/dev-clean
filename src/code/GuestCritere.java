package code;

import java.time.LocalDate;
import java.util.ArrayList;

public class GuestCritere extends Critere{
    private final boolean GUEST_HAS_ANIMAL_ALLERGY;
    private ArrayList<Food> listGuestFood;


    public GuestCritere(Gender gender, Gender paiGender, ArrayList<String> listHobbies, ArrayList<LocalDate> historique, boolean gUEST_HAS_ANIMAL_ALLERGY, ArrayList<Food> listGuestFood) {
        super(gender, paiGender, listHobbies, historique);
        GUEST_HAS_ANIMAL_ALLERGY = gUEST_HAS_ANIMAL_ALLERGY;
        this.listGuestFood = listGuestFood;
    }

    public GuestCritere(Gender gender, Gender paiGender, boolean gUEST_HAS_ANIMAL_ALLERGY, ArrayList<Food> listGuestFood){
        this(gender, paiGender, new ArrayList<String>() , new ArrayList<LocalDate>(), gUEST_HAS_ANIMAL_ALLERGY, listGuestFood);
    }

    public GuestCritere(Gender gender, Gender paiGender, boolean gUEST_HAS_ANIMAL_ALLERGY){
        this(gender, paiGender, new ArrayList<String>() , new ArrayList<LocalDate>(), gUEST_HAS_ANIMAL_ALLERGY, new ArrayList<Food>());
    }

    public boolean estAllergique(){
        return GUEST_HAS_ANIMAL_ALLERGY;
    }

    public void addFood(Food food){
        listGuestFood.add(food);
    }

    public void removeFood(Food food){
        listGuestFood.remove(food);
    }


    public ArrayList<Food> getListGuestFood() {
        return listGuestFood;
    }

    @Override
    public boolean getAnimal() {
        return GUEST_HAS_ANIMAL_ALLERGY;
    }

    @Override
    public String toStringFood() {
        String ch = "";
        for (Food food : listGuestFood) {
            ch += food.getLabel() + ',';
        }
        return trimFood(ch);
    }

    // pour retirer le dernier ","
    public static String trimFood(String ch){
        return ch.substring(0, ch.length()-1);
    } 
}
