import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import classes.Customer;
import classes.Invoice;

public class App {
    private static List<Customer> customers = new ArrayList<>();
    private static List<Invoice> invoices = new ArrayList<>();

    private static Scanner scanner;

    public static void main(String[] args) {
        customers.add(new Customer(1, "Mr A", 2));
        customers.add(new Customer(2, "Mr B", 5));
        customers.add(new Customer(3, "Mr C", 3));
        
        invoices.add(new Invoice(1, customers.get(0), 200));
        invoices.add(new Invoice(2, customers.get(0), 320));
        invoices.add(new Invoice(3, customers.get(1), 250));
        invoices.add(new Invoice(4, customers.get(1), 370));
        invoices.add(new Invoice(5, customers.get(2), 240));
        invoices.add(new Invoice(6, customers.get(2), 360));

        scanner = new Scanner(System.in);

        getInvoiceByCustomerId();
        System.out.println();
        getCustomerWithMaxTotalAmount();
        System.out.println();
        getCustomerWithMinDiscount();
        System.out.println();
        searchCustomer();

        scanner.close();
    }

    private static void getInvoiceByCustomerId() {
        System.out.print("Enter customer ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        invoices.stream().filter(x -> x.getCustomerId() == id).forEach(System.out::println);
    }

    private static void getCustomerWithMaxTotalAmount() {
        System.out.println("Customer with max total amount:");
        invoices.stream()
            .collect(Collectors.groupingBy(Invoice::getCustomer))
            .entrySet().stream()
            .map(x -> new Invoice(x.getKey().getId(), x.getKey(), x.getValue().stream().map(Invoice::getAmount).reduce(Double::sum).orElse(0.0)))
            .max(Comparator.comparing(Invoice::getAmount)).ifPresent(System.out::println);
    }

    private static void getCustomerWithMinDiscount() {
        System.out.println("Customer with min discount:");
        customers.stream().min(Comparator.comparing(Customer::getDiscount)).ifPresent(System.out::println);
    }

    private static void searchCustomer() {
        System.out.print("Enter search query: ");
        String query = scanner.nextLine();

        try {
            int id = Integer.parseInt(query);
            invoices.stream().filter(x -> x.getId() == id).findAny().ifPresent(x -> System.out.println(x.getCustomer()));
        } catch (NumberFormatException e) {}

        Pattern pattern = Pattern.compile(Pattern.quote(query), Pattern.CASE_INSENSITIVE);
        customers.stream().filter(x -> pattern.matcher(x.getName()).find()).forEach(System.out::println);
    }
}