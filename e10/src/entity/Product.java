package entity;

import exception.InvalidArgumentException;

public class Product {
    private String id;
    private String name;
    private int quantity;

    public Product(String id, String name, int price) throws InvalidArgumentException {
        setId(id);
        setName(name);
        setQuantity(quantity);
    }

    public String getId() {
        return id;
    }
    private void setId(String id) throws InvalidArgumentException {
        if (!id.matches("^(MS|NE|SE)[0-9]{6}$")) throw new InvalidArgumentException("Id must match ^(MS|NE|SE)[0-9]{6}$");
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) throws InvalidArgumentException {
        if (!name.matches("^[a-zA-Z\\s]{3,}$")) throw new InvalidArgumentException("Name must match ^[a-zA-Z\\s]{3,}$");
        this.name = name;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) throws InvalidArgumentException {
        if (quantity < 0) throw new InvalidArgumentException("Quantity can not be less than 0");
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=" + quantity + "]";
    }
}
