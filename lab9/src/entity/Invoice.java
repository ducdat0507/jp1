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
    public double getAmount() {
        return amount;
    }
    public LocalDateTime getInvoiceTime() {
        return invoiceTime;
    }

    @Override
    public String toString() {
        return "Invoice [id=" + id + ", account=" + account + ", amount=" + amount + ", invoiceTime=" + invoiceTime + "]";
    }

}
