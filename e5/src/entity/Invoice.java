package entity;

import java.time.LocalDateTime;

public class Invoice {
    private String id;
    private Account account;
    private double amount;
    private double discount;
    private LocalDateTime invoiceDate;

    public String getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }
    public Customer getCustomer() {
        return account.getCustomer();
    }
    public String getCustomerId() {
        return account.getCustomer().getId();
    }
    public String getCustomerName() {
        return account.getCustomer().getName();
    }

    public double getAmount() {
        return amount;
    }
    public double getAmountAfterDiscount() {
        return amount * (1 - discount);
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public double getDiscount() {
        return discount;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public LocalDateTime getInvoiceDate() {
        return invoiceDate;
    }
    public void setInvoiceDate(LocalDateTime invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    @Override
    public String toString() {
        return "Invoice [id=" + id + ", account=" + account + ", amount=" + amount + ", discount=" + discount + ", invoiceDate=" + invoiceDate
                + "]";
    }
    
    public Invoice(String id, Account account, double amount, LocalDateTime invoiceDate) {
        this.id = id;
        this.account = account;
        this.amount = amount;
        this.invoiceDate = invoiceDate;
    }
}
