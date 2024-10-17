package entity;

public enum OrderStatus {
    PENDING("Pending"),
    PAID("Paid"),
    CANCELLED("Cancelled"),
    COMPLETED("Completed");

    private final String label;

    private OrderStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
