package entity;

import java.time.LocalDateTime;

import exception.InvalidArgumentException;

public class Order {
    private String id;
    private Customer customer;
    private LocalDateTime orderTime;

    public Order(String id, Customer customer, LocalDateTime orderTime) throws InvalidArgumentException {
        setId(id);
        setCustomer(customer);
        setOrderTime(orderTime);
    }

    public String getId() {
        return id;
    }
    public void setId(String id) throws InvalidArgumentException {
        if (!id.matches("^(ORDPM)[0-9]{8}$")) throw new InvalidArgumentException("Name must match ^(ORDPM)[0-9]{8}$");
        this.id = id;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public LocalDateTime getOrderTime() {
        return orderTime;
    }
    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }
    
    @Override
    public String toString() {
        return "Order [id=" + id + ", customer=" + customer + ", orderTime=" + orderTime + "]";
    }
}
