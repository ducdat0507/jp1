import java.time.LocalDateTime;

import controller.BankingController;
import entity.Account;
import entity.Customer;
import entity.Gender;
import entity.Invoice;
import global.ANSI;
import global.Global;

public class Main {
    public static void main(String[] args) {
        BankingController controller = new BankingController();
        controller.addCustomer(new Customer(1, "John", Gender.MALE, 10));
        controller.addCustomer(new Customer(2, "Dave", Gender.MALE, 15));
        controller.addCustomer(new Customer(3, "Roxy", Gender.FEMALE, 8));
        controller.addCustomer(new Customer(4, "Jade", Gender.FEMALE, 12));

        controller.addAccount(new Account(1, controller.getCustomer(1), 2300));
        controller.addAccount(new Account(2, controller.getCustomer(2), 3400));
        controller.addAccount(new Account(3, controller.getCustomer(3), 4500));
        controller.addAccount(new Account(4, controller.getCustomer(4), 2300));

        controller.addInvoice(new Invoice(1, controller.getAccount(1), 1200, LocalDateTime.of(2024, 8, 30, 12, 34, 56)));
        controller.addInvoice(new Invoice(2, controller.getAccount(2), 1200, LocalDateTime.of(2024, 8, 30, 16, 34, 56)));
        controller.addInvoice(new Invoice(3, controller.getAccount(3), 1200, LocalDateTime.of(2024, 8, 31, 8, 34, 56)));
        controller.addInvoice(new Invoice(4, controller.getAccount(4), 1200, LocalDateTime.of(2024, 8, 31, 12, 34, 56)));
        controller.addInvoice(new Invoice(5, controller.getAccount(1), 1200, LocalDateTime.of(2024, 9, 1, 8, 34, 56)));
        controller.addInvoice(new Invoice(6, controller.getAccount(2), 1200, LocalDateTime.of(2024, 9, 1, 12, 34, 56)));
        controller.addInvoice(new Invoice(7, controller.getAccount(3), 1200, LocalDateTime.of(2024, 9, 1, 16, 34, 56)));
        controller.addInvoice(new Invoice(8, controller.getAccount(4), 1200, LocalDateTime.of(2024, 9, 1, 20, 34, 56)));

        System.out.println(
            ANSI.format(ANSI.BG_WHITE, ANSI.FG_BLACK, ANSI.BOLD)
            + " Welcome to banking program " + ANSI.format(ANSI.CLEAR));
        while (true) {
            System.out.println();
            
            Global.drawPrompt("Main menu", new String[][] {
                new String[] { "1", "List items" },
                new String[] { "2", "Update one item" },
                new String[] { "3", "Search items" },
                new String[] { "4", "Find accounts not in deficit" },
                new String[] { "5", "Find accounts in deficit" },
                new String[] { "6", "Women special discount" },
                new String[] { "0", "Exit" },
            });

            System.out.print("Enter choice: " + ANSI.format(ANSI.FG_LIGHT_YELLOW));
            String choice = System.console().readLine().trim();
            System.out.print(ANSI.format(ANSI.CLEAR));

            switch (choice) {
                case "1":
                    controller.doListItem();
                    break;

                case "2":
                    controller.doUpdate();
                    break;
            
                case "3":
                    controller.doSearch();
                    break;
            
                case "4":
                    controller.listAccountsNotInDebt();
                    break;
            
                case "5":
                    controller.listAccountsInDebt();
                    break;
            
                case "6":
                    controller.applyWomenDiscount(10);
                    break;
            
                case "0":
                    System.out.println("Goodbye");
                    return;
            
                default:
                    System.out.println(ANSI.format(ANSI.FG_LIGHT_RED) + "Invalid choice" + ANSI.format(ANSI.CLEAR));
                    break;
            }
        }
    }
}
