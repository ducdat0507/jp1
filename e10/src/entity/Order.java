package entity;

import java.time.LocalDateTime;

public class Order {
    private String id;
    private int customerId;
    private LocalDateTime orderTime;

    public Order(String id, int customerId, LocalDateTime orderTime) {
        setId(id);
        setCustomerId(customerId);
        setOrderTime(orderTime);
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public LocalDateTime getOrderTime() {
        return orderTime;
    }
    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }
    
    @Override
    public String toString() {
        return "Order [id=" + id + ", customerId=" + customerId + ", orderTime=" + orderTime + "]";
    }
}
