package entity;

import exception.InvalidArgumentException;

public class OrderDetail {
    private int id;
    private Order order;
    private Product product;
    private int quantity;
    private OrderStatus status;

    public OrderDetail(int id, Order order, Product product, int quantity) throws InvalidArgumentException {
        setId(id);
        setOrder(order);
        setProduct(product);
        setQuantity(quantity);
        setStatus(OrderStatus.PENDING);
    }

    public int getId() {
        return id;
    }
    private void setId(int id) {
        this.id = id;
    }
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) throws InvalidArgumentException {
        if (quantity < 0) throw new InvalidArgumentException("Quantity can not be less than 0");
        this.quantity = quantity;
    }
    public OrderStatus getStatus() {
        return status;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
