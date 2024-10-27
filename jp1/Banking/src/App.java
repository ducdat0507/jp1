import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.AccountController;
import controller.BankingController;
import entity.Account;
import entity.Customer;
import entity.Gender;
import entity.Invoice;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("1", "Adsads", Gender.MALE));
        customers.add(new Customer("2", "Wgnjbvm", Gender.FEMALE));

        Account acc = new Account("000000000001", customers.get(0), 2000);
        AccountController accController = new AccountController(acc);
        
        input = scanner.nextLine();
        accController.depositString(input);
        System.out.println(acc);
        
        input = scanner.nextLine();
        accController.withdrawString(input);
        System.out.println(acc);
    }
}
