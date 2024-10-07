package entity;

import java.time.LocalDateTime;

public class Invoice {
    private int id;
    private Account account;
    private double amount;
    private LocalDateTime invoiceTime;
    
    public Invoice(int id, Account account, double amount) {
        this(id, account, amount, LocalDateTime.now());
    }
    public Invoice(int id, Account account, double amount, LocalDateTime invoiceTime) {
        this.id = id;
        this.account = account;
        this.amount = amount;
        this.invoiceTime = invoiceTime;
    }

    public int getId() {
        return id;
    }
    public Account getAccount() {
        return account;
    }
    public Customer getCustomer() {
        return account.getCustomer();
    }
    public int getCustomerId() {
        return account.getCustomer().getId();
    }
    public String getCustomerName() {
        return account.getCustomer().getName();
    }
    public double getCustomerDiscount() {
        return account.getCustomer().getDiscount();
    }
    public double getAmount() {
        return amount;
    }
    public double getAmountAfterDiscount() {
        return amount * (1 - getCustomerDiscount() / 100);
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public LocalDateTime getInvoiceTime() {
        return invoiceTime;
    }

    @Override
    public String toString() {
        return "Invoice [id=" + id + ", account=" + account + ", amount=" + amount + ", invoiceTime=" + invoiceTime + "]";
    }

}
