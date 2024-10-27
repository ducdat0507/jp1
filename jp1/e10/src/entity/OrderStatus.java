package entity;

public enum OrderStatus {
    PENDING("Pending"),
    PAID("Paid"),
    CANCELLED("Cancelled"),
    COMPLETED("Completed");

    private String label;

    public String getLabel() {
        return label;
    }

    OrderStatus(String label) {
        this.label = label;
    }
}
