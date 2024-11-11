package Entity;

import IGeneral.Entity.Entity;

public class Product extends Entity {
    private String name;
    private double price;
    private int quantity;

    public Product() {}
    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    public Product(long id, String name, double price, int quantity) {
        super(id);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    

    @Override
    public String toString() {
        return id + Entity.PROPERTY_SEPARATOR
            + name + Entity.PROPERTY_SEPARATOR 
            + price + Entity.PROPERTY_SEPARATOR 
            + quantity;
    }
}
