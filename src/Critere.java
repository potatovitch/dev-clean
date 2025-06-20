public enum Critere {
    GUEST_ANIMAL_ALLERGY,
    HOST_HAS_ANIMAL,
    GUEST_FOOD,
    HOST_FOOD,
    HOBBIES,
    GENDER,
    PAIR_GENDER,
    HISTORY;

    public char getType() {
        switch(this) {
            case GUEST_ANIMAL_ALLERGY:
            case HOST_HAS_ANIMAL:
                return 'B'; // Booléen
            case GUEST_FOOD:
            case HOST_FOOD:
            case HOBBIES:
            case GENDER:
            case PAIR_GENDER:
            case HISTORY:
                return 'T'; // Texte
            default:
                return 'T';
        }
    }
}