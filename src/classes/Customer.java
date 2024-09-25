package classes;

public class Customer {
    private int id;
    private String name;
    private int discount;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getDiscount() {
        return discount;
    }
    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Customer(int id, String name, int discount) {
        this.id = id;
        this.name = name;
        this.discount = discount;
    }

    @Override
    public String toString() {
        return this.name + "(" + this.id + ")" + "(" + this.discount + "%)";
    }

}
