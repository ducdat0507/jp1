import java.time.LocalDateTime;
import java.util.Scanner;

import controller.BankingController;
import entity.Account;
import entity.Customer;
import entity.Gender;
import entity.Invoice;

public class App {
    private static BankingController controller;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choice, query;

        controller = new BankingController();

        controller.addCustomer(new Customer("1", "Askjasdjasdj", Gender.MALE));
        controller.addCustomer(new Customer("2", "askjdsadk asdasd", Gender.FEMALE));
        controller.addCustomer(new Customer("3", "klsdfjkljk vgmrwewe", Gender.MALE));
        controller.addCustomer(new Customer("4", "qwieop isfk mwe", Gender.FEMALE));

        controller.addAccount(new Account("1", controller.getCustomer(0), 600));
        controller.addAccount(new Account("2", controller.getCustomer(1), 720));
        controller.addAccount(new Account("3", controller.getCustomer(2), 810));
        controller.addAccount(new Account("4", controller.getCustomer(3), 750));

        controller.addInvoice(new Invoice("1", controller.getAccount(0), 600, LocalDateTime.parse("2024-07-29T09:17")));
        controller.addInvoice(new Invoice("2", controller.getAccount(1), 710, LocalDateTime.parse("2024-07-30T12:34")));
        controller.addInvoice(new Invoice("4", controller.getAccount(2), 820, LocalDateTime.parse("2024-08-02T16:55")));
        controller.addInvoice(new Invoice("3", controller.getAccount(3), 730, LocalDateTime.parse("2024-08-03T14:22")));

        System.out.println("\033[30;107m  Welcome to banking program  \033[0m");

        for (;;) {
            System.out.println("------------------------------");
            System.out.println("- [1] Show data");
            System.out.println("- [2] Search account");
            System.out.println("- [3] Search invoice");
            System.out.println("- [4] List customers not in debt (discount applied)");
            System.out.println("- [5] List customers in debt (discount not applied)");
            System.out.println("- [6] Apply women discount");
            System.out.println("- [0] Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextLine();
            System.out.println("------------------------------");
            switch (choice) {
                case "1":
                    controller.showData();
                    break;

                case "2":
                    System.out.print("Search account by id or customer name\nEnter query: ");
                    query = scanner.nextLine();
                    controller.findAccounts(query);
                    break;

                case "3":
                    System.out.print("Search invoice by id or customer name\nEnter query: ");
                    query = scanner.nextLine();
                    controller.findInvoices(query);
                    break;

                case "4":
                    controller.listAccountsNotInDebt();
                    break;

                case "5":
                    controller.listAccountsInDebt();
                    break;

                case "6":
                    controller.applyWomenDiscount();
                    break;
            
                case "0":
                    scanner.close();
                    return;
            
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
}
