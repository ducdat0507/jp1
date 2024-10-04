package Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Account {
    private int id;
    private String accountNumber;
    private double balance;
    private LocalDateTime createAt;
    private Customer customer;

    public Account() {} 
    public Account(int id, String accountNumber, double balance, LocalDateTime createAt, Customer customer) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.createAt = createAt;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    @Override
    public String toString() {
        return "Account [id=" + id + ", accountNumber=" + accountNumber + ", balance=" + balance + ", createAt="
                + createAt + ", customer=" + customer + "]";
    }
}
