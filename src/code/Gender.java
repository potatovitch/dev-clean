package code;

public enum Gender {
    MALE("Male"),
    female("Female");

    Gender(String label){}

    public String getLabel(){
        return name();
    }
}
