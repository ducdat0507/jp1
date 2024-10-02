package entity;

public class Account {
    private String id;
    private Customer customer;
    private double balance;

    public String getId() {
        return id;
    }
    public Customer getCustomer() {
        return customer;
    }
    public String getCustomerName() {
        return customer.getName();
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    @Override
    public String toString() {
        return customer.toString() + " balance=" + String.format("%#.2f", balance);
    }
    
    public Account(String id, Customer customer, double balance) {
        this.id = id;
        this.customer = customer;
        this.balance = balance;
    }

}
