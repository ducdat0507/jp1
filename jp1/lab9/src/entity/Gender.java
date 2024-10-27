package entity;

public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other"),
    UNKNOWN("Unknown");
    
    private String label;
    public String getLabel() {
        return label;
    }

    Gender(String label) {
        this.label = label;
    }
}
