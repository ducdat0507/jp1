package entity;

public class Customer {
    private int id;
    private String name;

    public Customer(int id, String name) {
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
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + "]";
    }
    
}
