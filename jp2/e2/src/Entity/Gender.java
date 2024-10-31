package Entity;

public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    UNKNOWN("Unknown"),
    OTHER("Other");

    private String label;
    Gender(String label) {
        this.label = label;
    }
    public String getLabel() {
        return label;
    }
}
