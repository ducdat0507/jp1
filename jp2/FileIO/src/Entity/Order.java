package Entity;

import java.time.LocalDateTime;

import IGeneral.Entity.Entity;

public class Order extends Entity {
    private long customerId;
    private LocalDateTime orderTime;
    private LocalDateTime completeTime;

    public Order() {}
    public Order(long customerId, LocalDateTime orderTime, LocalDateTime completeTime) {
        this.customerId = customerId;
        this.orderTime = orderTime;
        this.completeTime = completeTime;
    }
    public Order(long id, long customerId, LocalDateTime orderTime, LocalDateTime completeTime) {
        super(id);
        this.customerId = customerId;
        this.orderTime = orderTime;
        this.completeTime = completeTime;
    }

    public long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
    public LocalDateTime getOrderTime() {
        return orderTime;
    }
    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }
    public LocalDateTime getCompleteTime() {
        return completeTime;
    }
    public void setCompleteTime(LocalDateTime completeTime) {
        this.completeTime = completeTime;
    }

    @Override
    public String toString() {
        return id + Entity.PROPERTY_SEPARATOR 
            + customerId + Entity.PROPERTY_SEPARATOR 
            + orderTime + Entity.PROPERTY_SEPARATOR 
            + completeTime;
    }
}
