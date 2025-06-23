package code;

public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    NONBIN("Non-binaire");

    Gender(String label){}

    public String getLabel(){
        return name();
    }
}
