package code;

import java.time.LocalDate;
import java.util.ArrayList;

public class HostCritere extends Critere{
    private final boolean HOST_HAS_ANIMAL;
    private ArrayList<Food> listHostFood;


    public HostCritere(Gender gender, Gender paiGender, ArrayList<String> listHobbies, ArrayList<LocalDate> historique, boolean hOST_HAS_ANIMAL, ArrayList<Food> listHostFood) {
        super(gender, paiGender, listHobbies, historique);
        HOST_HAS_ANIMAL = hOST_HAS_ANIMAL;
        this.listHostFood = listHostFood;
    }

    public HostCritere(Gender gender, Gender paiGender, boolean hOST_HAS_ANIMAL, ArrayList<Food> listHostFood){
        this(gender, paiGender, new ArrayList<String>() , new ArrayList<LocalDate>(), hOST_HAS_ANIMAL, listHostFood);
    }

    public HostCritere(Gender gender, Gender paiGender, boolean hOST_HAS_ANIMAL){
        this(gender, paiGender, new ArrayList<String>() , new ArrayList<LocalDate>(), hOST_HAS_ANIMAL, new ArrayList<Food>());
    }

    public boolean aUnAnimal(){
        return HOST_HAS_ANIMAL;
    }

    public void addFood(Food food){
        listHostFood.add(food);
    }

    public void removeFood(Food food){
        listHostFood.remove(food);
    }

    public ArrayList<Food> getListHostFood(){
        return listHostFood;
    }

    public boolean isGoodFood(GuestCritere c){
        for (Food food : listHostFood) {
            if ( c.getListGuestFood().contains(food)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean getAnimal() {
        return HOST_HAS_ANIMAL;
    }

    @Override
    public String toStringFood() {
        String ch = "";
        for (Food food : listHostFood) {
            ch += food.getLabel() + ',';
        }
        return trimFood(ch);
    }

    // pour retirer le dernier ","
    public static String trimFood(String ch){
        return ch.substring(0, ch.length()-1);
    }
}
