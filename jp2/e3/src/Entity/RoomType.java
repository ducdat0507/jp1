package Entity;

public enum RoomType {
    SINGLE("Single"),
    DOUBLE("Double"),
    QUEEN("Queen"),
    TRIPLE("Triple"),
    QUAD("Quad");

    private String label;
    RoomType(String label) {
        this.label = label;
    }
    public String getLabel() {
        return label;
    }
}
