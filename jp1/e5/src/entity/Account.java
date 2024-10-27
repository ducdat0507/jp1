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

    public void deposit(double amount) {
        if (amount <= 0) throw new Error("Amount must be greater than 0");
        balance += amount;
    }
    public void withdraw(double amount) {
        if (amount <= 0) throw new Error("Amount must be greater than 0");
        if (amount > balance) throw new Error("Amount exceeds account balance");
        balance -= amount;
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
