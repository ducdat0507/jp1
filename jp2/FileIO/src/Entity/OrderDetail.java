package Entity;

import IGeneral.Entity.Entity;

public class OrderDetail extends Entity {
    private long orderId;
    private long productId;
    private int quantity;
    private double unitPrice;

    public OrderDetail() {}
    public OrderDetail(long orderId, long productId, int quantity, double unitPrice) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
    public OrderDetail(long id, long orderId, long productId, int quantity, double unitPrice) {
        super(id);
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public long getOrderId() {
        return orderId;
    }
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
    public long getProductId() {
        return productId;
    }
    public void setProductId(long productId) {
        this.productId = productId;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    
    @Override
    public String toString() {
        return id + Entity.PROPERTY_SEPARATOR
            + orderId + Entity.PROPERTY_SEPARATOR 
            + productId + Entity.PROPERTY_SEPARATOR 
            + quantity + Entity.PROPERTY_SEPARATOR 
            + unitPrice;
    }
}
