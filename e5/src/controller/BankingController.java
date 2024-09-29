package controller;

import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.stream.Collectors;

import entity.Account;
import entity.Customer;
import entity.Gender;
import entity.Invoice;

public class BankingController {
    private final List<Customer> customers;
    private final List<Account> accounts;
    private final List<Invoice> invoices;

    public BankingController() {
        this.customers = new ArrayList<>();
        this.accounts = new ArrayList<>();
        this.invoices = new ArrayList<>();
    }
    
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }
    public void addAccount(Account account) {
        accounts.add(account);
    }
    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }
    
    public Customer getCustomer(int index) {
        return customers.get(index);
    }
    public Account getAccount(int index) {
        return accounts.get(index);
    }
    public Invoice getInvoice(int index) {
        return invoices.get(index);
    }
    
    public Stream<Customer> streamCustomer() {
        return customers.stream();
    }
    public Stream<Account> streamAccount() {
        return accounts.stream();
    }
    public Stream<Invoice> streamInvoice() {
        return invoices.stream();
    }

    public void showData() {
        System.out.println("Customers:");
        customers.stream().sorted(Comparator.comparing(Customer::getName)).forEach(System.out::println);
        System.out.println("\nAccounts:");
        accounts.stream().forEach(System.out::println);
        System.out.println("\nInvoices:");
        invoices.stream().forEach(System.out::println);
    }

    public void findAccounts(String query) {
        Pattern pattern = Pattern.compile(Pattern.quote(query), Pattern.CASE_INSENSITIVE);
        accounts.stream().filter(x -> (x.getId().equals(query) || pattern.matcher(x.getCustomerName()).find()))
            .forEach(System.out::println);
    }
    public void findInvoices(String query) {
        Pattern pattern = Pattern.compile(Pattern.quote(query), Pattern.CASE_INSENSITIVE);
        invoices.stream().filter(x -> (x.getId().equals(query) || pattern.matcher(x.getCustomerName()).find()))
            .forEach(System.out::println);
    }

    public void listAccountsNotInDebt() {
        invoices.stream()
            .collect(Collectors.groupingBy(Invoice::getAccount, Collectors.summingDouble(Invoice::getAmountAfterDiscount)))
            .entrySet().stream()
            .collect(Collectors.toMap(x -> x.getKey(), x -> x.getKey().getBalance() - x.getValue()))
            .entrySet().stream()
            .filter(x -> x.getValue() >= 0).map(x -> x.getKey())
            .forEach(System.out::println);
    }

    public void listAccountsInDebt() {
        invoices.stream()
            .collect(Collectors.groupingBy(Invoice::getAccount, Collectors.summingDouble(Invoice::getAmount)))
            .entrySet().stream()
            .collect(Collectors.toMap(x -> x.getKey(), x -> x.getKey().getBalance() - x.getValue()))
            .entrySet().stream()
            .filter(x -> x.getValue() < 0).map(x -> x.getKey())
            .forEach(System.out::println);
    }

    public void applyWomenDiscount() {
        invoices.stream()
            .filter(x -> x.getCustomer().getGender() == Gender.FEMALE && x.getInvoiceDate().getMonth() == Month.AUGUST)
            .forEach(x -> x.setDiscount(1 - (1 - x.getDiscount()) * 0.9));
        System.out.println("Applied discount to invoices");
    }
}
