package Entity;

import IGeneral.Entity.Entity;

public class Customer extends Entity {
    private String name;

    public Customer() {}
    public Customer(String name) {
        this.name = name;
    }
    public Customer(long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return id + Entity.PROPERTY_SEPARATOR 
            + name;
    }
}
