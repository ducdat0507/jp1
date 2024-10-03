package Entity;

import java.time.LocalDateTime;

public class Account {
    private int id;
    private String accountNumber;
    private Customer customer;
    private double balance;
    private LocalDateTime creationDate;

    public Account() {}
    public Account(int id, String accountNumber, Customer customer, double balance) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.balance = balance;
        this.creationDate = LocalDateTime.now();
    }
    
    public int getId() {
        return id;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public Customer getCustomer() {
        return customer;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    
}
