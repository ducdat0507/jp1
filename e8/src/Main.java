import Controller.AccountController;
import Controller.CustomerController;
import Controller.StaffController;
import Entity.Account;
import Entity.Customer;
import Entity.Gender;
import Entity.Staff;
import Service.AccountService;
import Service.CustomerService;
import Service.StaffService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        List<Customer> customers = new ArrayList<>();
        CustomerService cs = new CustomerService(customers);
        CustomerController customerController = new CustomerController(cs);

        customers.add(new Customer(1,"VCB01", "Binh", Gender.MALE));
        customers.add(new Customer(2,"TCB002","Minh", Gender.FEMALE));

        customerController.getByCode("vcb01")
            .ifPresentOrElse(System.out::println, () -> System.out.println("Customer not found"));
            System.out.println("-----");
        customerController.getById(3)
            .ifPresentOrElse(System.out::println, () -> System.out.println("Customer not found"));
            System.out.println("-----");
        customerController.getByName("Inh")
            .forEach(System.out::println);

        // -----------
        System.out.println();
        // -----------

        List<Staff> staffs = new ArrayList<>();
        StaffService ss = new StaffService(staffs);
        StaffController staffController = new StaffController(ss);

        staffs.add(new Staff(1,"VCB01", "Binh", LocalDateTime.of(2002, 1, 10, 0, 0, 0)));
        staffs.add(new Staff(2,"TCB002","Minh", LocalDateTime.of(2002, 1, 10, 0, 0, 0)));

        staffController.getByCode("tcb002")
            .ifPresentOrElse(System.out::println, () -> System.out.println("Staff not found"));
            System.out.println("-----");
        staffController.getById(2)
            .ifPresentOrElse(System.out::println, () -> System.out.println("Staff not found"));
            System.out.println("-----");
        staffController.getByName("Inh")
            .forEach(System.out::println);

        // -----------
        System.out.println();
        // -----------

        List<Account> accounts = new ArrayList<>();
        AccountService as = new AccountService(accounts);
        AccountController accountController = new AccountController(as);

        accounts.add(new Account(1, "AC00001", 2300, LocalDateTime.of(2022, 10, 23, 10, 0, 0), customers.get(0)));
        accounts.add(new Account(2, "AC00002", 2303450, LocalDateTime.of(2022, 10, 23, 10, 0, 0), customers.get(0)));
        accounts.add(new Account(3, "AC00003", 2300, LocalDateTime.of(2022, 10, 23, 10, 0, 0), customers.get(1)));
        accounts.add(new Account(4, "AC00004", 230100, LocalDateTime.of(2022, 10, 23, 10, 0, 0), customers.get(1)));

        accountController.getByCustomerCode("vcb01")
            .forEach(System.out::println);
        System.out.println("-----");
        accountController.getByCustomerName("minh")
            .forEach(System.out::println);
        System.out.println("-----");
        accountController.getAccountByCustomer()
            .forEach(System.out::println);
        System.out.println("-----");
        System.out.println(accountController.countAccountByCustomer());
        System.out.println("-----");
        accountController.getMaxBalance()
            .ifPresentOrElse(System.out::println, () -> System.out.println("Account not found"));
    }
}