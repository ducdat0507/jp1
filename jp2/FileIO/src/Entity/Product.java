package Entity;

import IGeneral.Entity.Entity;

public class Product extends Entity {
    private String name;
    private String price;

    public Product() {}
    public Product(String name, String price) {
        this.name = name;
        this.price = price;
    }
    public Product(long id, String name, String price) {
        super(id);
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    

    @Override
    public String toString() {
        return id + Entity.PROPERTY_SEPARATOR
            + name + Entity.PROPERTY_SEPARATOR 
            + price;
    }
}
