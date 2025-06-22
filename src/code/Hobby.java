package code;

public enum Hobby {
    // TODO : trouver des hobby en plus si besoin
    SPORT("sport"),
    MUSIQUE("musique"),
    CINEMA("cinéma");

    Hobby(String label){}

    public String getLabel(){
        return name();
    }
}
