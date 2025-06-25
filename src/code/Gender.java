package code;

public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other");

    Gender(String label){}

    public String getLabel(){
        return name();
    }
}
