package entity;

public class Customer {
    private int id;
    private String name;
    private Gender gender;
    private double discount;
    
    public Customer(int id, String name, Gender gender, double discount) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public double getDiscount() {
        return discount;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", gender=" + gender + ", discount=" + discount + "]";
    }
    
}
