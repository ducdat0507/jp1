package entity;

import exception.ValidationException;

public class OrderDetail {
    private int id;
    private String orderId;
    private String productId;
    private int quantity;
    private OrderStatus status;

    public OrderDetail(int id, String orderId, String productId, int quantity) {
        setId(id);
        setOrderId(orderId);
        setProductId(productId);
        setQuantity(quantity);
        setStatus(OrderStatus.PENDING);
    }

    public int getId() {
        return id;
    }
    private void setId(int id) {
        this.id = id;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public OrderStatus getStatus() {
        return status;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
