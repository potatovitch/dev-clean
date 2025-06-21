package code;

public enum Critere {
    GUEST_ANIMAL_ALLERGY,
    HOST_HAS_ANIMAL,
    GUEST_FOOD,
    HOST_FOOD,
    HAS_HOBBIES,
    GENDER,
    PAIR_GENDER,
    HISTORY;

    public char getType() {
        switch(this) {
            case GUEST_ANIMAL_ALLERGY:
            case HOST_HAS_ANIMAL:
            case HAS_HOBBIES:
                return 'B'; // Booléen
            case GUEST_FOOD:
            case HOST_FOOD:
            case GENDER:
            case PAIR_GENDER:
            case HISTORY:
                return 'T'; // Texte
            default:
                return 'T';
        }
    }

    public String getPersonType(){
        switch(this) {
            case GUEST_ANIMAL_ALLERGY:
            case GUEST_FOOD:
                return "Guest";
            case HOST_HAS_ANIMAL:
            case HOST_FOOD:
                return "Host";
            case HAS_HOBBIES:
            case GENDER:
            case PAIR_GENDER:
            case HISTORY:
                return "Person";
            default:
                return "Person";
        }
    }
}