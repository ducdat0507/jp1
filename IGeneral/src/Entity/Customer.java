package Entity;

public class Customer {
    private int id;
    private String code;
    private String name;

    public Customer() {}
    public Customer(int id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public String getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "Customer [id=" + id + ", code=" + code + ", name=" + name + "]";
    }
}
