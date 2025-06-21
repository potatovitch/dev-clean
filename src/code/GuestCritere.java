package code;

import java.time.LocalDate;
import java.util.ArrayList;

public class GuestCritere extends Critere{
    private final boolean GUEST_HAS_ANIMAL_ALLERGY;
    private ArrayList<String> listGuestFood;


    public GuestCritere(Gender gender, Gender paiGender, ArrayList<String> listHobbies, ArrayList<LocalDate> historique, boolean gUEST_HAS_ANIMAL_ALLERGY, ArrayList<String> listGuestFood) {
        super(gender, paiGender, listHobbies, historique);
        GUEST_HAS_ANIMAL_ALLERGY = gUEST_HAS_ANIMAL_ALLERGY;
        this.listGuestFood = listGuestFood;
    }

    public GuestCritere(Gender gender, Gender paiGender, boolean gUEST_HAS_ANIMAL_ALLERGY, ArrayList<String> listGuestFood){
        this(gender, paiGender, new ArrayList<String>() , new ArrayList<LocalDate>(), gUEST_HAS_ANIMAL_ALLERGY, listGuestFood);
    }

    public boolean estAllergique(){
        return GUEST_HAS_ANIMAL_ALLERGY;
    }

    public void addFood(String food){
        listGuestFood.add(food);
    }

    public void removeFood(String food){
        listGuestFood.remove(food);
    }


    public ArrayList<String> getListGuestFood() {
        return listGuestFood;
    }
}
