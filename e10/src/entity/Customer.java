package entity;

import exception.InvalidArgumentException;

public class Customer {
    private int id;
    private String name;

    public Customer(int id, String name) throws InvalidArgumentException {
        setId(id);
        setName(name);
    }

    public int getId() {
        return id;
    }
    private void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) throws InvalidArgumentException {
        if (!name.matches("^[a-zA-Z\\s]{3,50}$")) throw new InvalidArgumentException("Name must match ^[a-zA-Z\\s]{3,50}$");
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + "]";
    }
    
}
