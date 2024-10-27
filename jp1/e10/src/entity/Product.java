package entity;

public class Product {
    private String id;
    private String name;
    private int quantity;

    public Product(String id, String name, int quantity) {
        setId(id);
        setName(name);
        setQuantity(quantity);
    }

    public String getId() {
        return id;
    }
    private void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=" + quantity + "]";
    }
}
