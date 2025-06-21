package code;

public enum Hobby {
    // TODO : changer les hobby
    HOBBY1("hobby1"),
    HOBBY2("hobby2"),
    HOBBY3("hobby3");

    Hobby(String label){}

    public String getLabel(){
        return name();
    }
}
