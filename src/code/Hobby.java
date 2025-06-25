package code;

public enum Hobby {
    SPORT("sport"),
    MUSIQUE("musique"),
    CINEMA("cinéma");

    Hobby(String label){}

    public String getLabel(){
        return name();
    }
}
