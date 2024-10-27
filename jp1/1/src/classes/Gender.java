package classes;

public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other");

    private String name;
    public String getName() {
        return name;
    }

    private Gender (String name) {
        this.name = name;
    }
}
