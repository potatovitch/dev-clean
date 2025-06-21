package code;

import java.time.LocalDate;
import java.util.ArrayList;

public class HostCritere extends Critere{
    private final boolean HOST_HAS_ANIMAL;
    private ArrayList<String> listHostFood;


    public HostCritere(Gender gender, Gender paiGender, ArrayList<String> listHobbies, ArrayList<LocalDate> historique, boolean hOST_HAS_ANIMAL, ArrayList<String> listHostFood) {
        super(gender, paiGender, listHobbies, historique);
        HOST_HAS_ANIMAL = hOST_HAS_ANIMAL;
        this.listHostFood = listHostFood;
    }

    public HostCritere(Gender gender, Gender paiGender, boolean hOST_HAS_ANIMAL, ArrayList<String> listGuestFood){
        this(gender, paiGender, new ArrayList<String>() , new ArrayList<LocalDate>(), hOST_HAS_ANIMAL, listGuestFood);
    }

    public boolean aUnAnimal(){
        return HOST_HAS_ANIMAL;
    }

    public void addFood(String food){
        listHostFood.add(food);
    }

    public void removeFood(String food){
        listHostFood.remove(food);
    }

    public ArrayList<String> getListHostFood(){
        return listHostFood;
    }

    public boolean isGoodFood(GuestCritere c){
        for (String food : listHostFood) {
            if ( c.getListGuestFood().contains(food)){
                return true;
            }
        }
        return false;
    }  
}
