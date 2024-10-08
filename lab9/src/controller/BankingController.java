package controller;

import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import entity.Account;
import entity.Customer;
import entity.Gender;
import entity.Invoice;
import global.ANSI;
import global.Global;
import igeneric.IListItemController;
import igeneric.ISearchController;
import igeneric.IUpdateController;
import service.AccountService;
import service.CustomerService;
import service.InvoiceService;

public class BankingController {
    private CustomerService customerService;
    private AccountService accountService;
    private InvoiceService invoiceService;

    private long counter;

    public BankingController() {
        customerService = new CustomerService();
        accountService = new AccountService();
        invoiceService = new InvoiceService();
    }

    public void addCustomer(Customer customer) {
        customerService.add(customer);
    }
    public void addAccount(Account account) {
        accountService.add(account);
    }
    public void addInvoice(Invoice invoice) {
        invoiceService.add(invoice);
    }

    public Customer getCustomer(int index) {
        return customerService.get(index);
    }
    public Account getAccount(int index) {
        return accountService.get(index);
    }
    public Invoice getInvoice(int index) {
        return invoiceService.get(index);
    }

    public void doUpdate() {
        IUpdateController updateController = promptUpdateController();
        if (updateController == null) return;
        updateController.promptAndUpdate();
    }

    private IUpdateController promptUpdateController() {
        while (true) {
            System.out.println();
            
            Global.drawPrompt("Select update target", new String[][] {
                new String[] { "1", "Customer" },
                new String[] { "2", "Account" },
                new String[] { "3", "Invoice" },
                new String[] { "0", "Abort" },
            });

            System.out.print("Enter choice: " + ANSI.format(ANSI.FG_LIGHT_YELLOW));
            String choice = System.console().readLine().trim();
            System.out.print(ANSI.format(ANSI.CLEAR));

            switch (choice) {
                case "1":
                    return new CustomerUpdateController(customerService);

                case "2":
                    return new AccountUpdateController(accountService);

                case "3":
                    return new InvoiceUpdateController(invoiceService);
            
                case "0":
                    return null;
            
                default:
                    System.out.println(ANSI.format(ANSI.FG_LIGHT_RED) + "Invalid choice" + ANSI.format(ANSI.CLEAR));
                    break;
            }
        }
    }

    public void doListItem() {
        IListItemController listitemController = promptListItemController();
        if (listitemController == null) return;
        listitemController.list();
    }

    private IListItemController promptListItemController() {
        while (true) {
            System.out.println();
            
            Global.drawPrompt("Select listitem target", new String[][] {
                new String[] { "1", "Customer (default sort)" },
                new String[] { "1A", "Customer (sort by name)" },
                new String[] { "1B", "Customer (sort by discount)" },
                new String[] { "2", "Account (default sort)" },
                new String[] { "2A", "Account (sort by customer name)" },
                new String[] { "2B", "Account (sort by balance)" },
                new String[] { "3", "Invoice (default sort)" },
                new String[] { "3A", "Account (sort by customer name)" },
                new String[] { "3B", "Account (sort by amount)" },
                new String[] { "3C", "Account (sort by amount after discount)" },
                new String[] { "0", "Abort" },
            });

            System.out.print("Enter choice: " + ANSI.format(ANSI.FG_LIGHT_YELLOW));
            String choice = System.console().readLine().trim();
            System.out.print(ANSI.format(ANSI.CLEAR));

            switch (choice.toUpperCase()) {
                case "1":
                    return new ListItemController<>(customerService);
                case "1A":
                    return new ListItemController<>(customerService, Comparator.comparing(Customer::getName));
                case "1B":
                    return new ListItemController<>(customerService, Comparator.comparing(Customer::getDiscount));

                case "2":
                    return new ListItemController<>(accountService);
                case "2A":
                    return new ListItemController<>(accountService, Comparator.comparing(Account::getCustomerName));
                case "2B":
                    return new ListItemController<>(accountService, Comparator.comparing(Account::getBalance));

                case "3":
                    return new ListItemController<>(invoiceService);
                case "3A":
                    return new ListItemController<>(invoiceService, Comparator.comparing(Invoice::getCustomerName));
                case "3B":
                    return new ListItemController<>(invoiceService, Comparator.comparing(Invoice::getAmount));
                case "3C":
                    return new ListItemController<>(invoiceService, Comparator.comparing(Invoice::getAmountAfterDiscount));
            
                case "0":
                    return null;
            
                default:
                    System.out.println(ANSI.format(ANSI.FG_LIGHT_RED) + "Invalid choice" + ANSI.format(ANSI.CLEAR));
                    break;
            }
        }
    }

    public void doSearch() {
        ISearchController searchController = promptSearchController();
        if (searchController == null) return;
        searchController.promptAndSearch();
    }

    private ISearchController promptSearchController() {
        while (true) {
            System.out.println();
            
            Global.drawPrompt("Select search target", new String[][] {
                new String[] { "1", "Customer" },
                new String[] { "2", "Account" },
                new String[] { "3", "Invoice" },
                new String[] { "0", "Abort" },
            });

            System.out.print("Enter choice: " + ANSI.format(ANSI.FG_LIGHT_YELLOW));
            String choice = System.console().readLine().trim();
            System.out.print(ANSI.format(ANSI.CLEAR));

            switch (choice) {
                case "1":
                    return new SearchController<>(customerService);

                case "2":
                    return new SearchController<>(accountService);

                case "3":
                    return new SearchController<>(invoiceService);
            
                case "0":
                    return null;
            
                default:
                    System.out.println(ANSI.format(ANSI.FG_LIGHT_RED) + "Invalid choice" + ANSI.format(ANSI.CLEAR));
                    break;
            }
        }
    }

    
    public void listAccountsNotInDebt() {
        List<Account> result = invoiceService.listAccountsNotInDebt();
        long length = result.size();
        counter = 0;
        if (length > 0) {
            System.out.println(ANSI.format(ANSI.BOLD, ANSI.UNDERLINE) + "Found " + length + " result(s):" + ANSI.format(ANSI.CLEAR));
            result.forEach(x -> System.out.println(
                String.format("%s% 5d) %s%s", ANSI.format(ANSI.FG_DARK_GRAY), ++counter, ANSI.format(ANSI.CLEAR), x.toString()))
            );
        } else {
            System.out.println(ANSI.format(ANSI.BOLD, ANSI.UNDERLINE, ANSI.FG_LIGHT_RED) + "Found no results" + ANSI.format(ANSI.CLEAR));
        }
    }

    public void listAccountsInDebt() {
        List<Account> result = invoiceService.listAccountsInDebt();
        long length = result.size();
        counter = 0;
        if (length > 0) {
            System.out.println(ANSI.format(ANSI.BOLD, ANSI.UNDERLINE) + "Found " + length + " result(s):" + ANSI.format(ANSI.CLEAR));
            result.forEach(x -> System.out.println(
                String.format("%s% 5d) %s%s", ANSI.format(ANSI.FG_DARK_GRAY), ++counter, ANSI.format(ANSI.CLEAR), x.toString()))
            );
        } else {
            System.out.println(ANSI.format(ANSI.BOLD, ANSI.UNDERLINE, ANSI.FG_LIGHT_RED) + "Found no results" + ANSI.format(ANSI.CLEAR));
        }
    }

    public void applyWomenDiscount(double percent) {
        List<Invoice> result = invoiceService.listApplicableWomenDiscount();
        result.forEach(x -> x.setAmount(x.getAmount() * (1 - percent / 100)));
        long length = result.size();
        counter = 0;
        if (length > 0) {
            System.out.println(ANSI.format(ANSI.BOLD, ANSI.UNDERLINE) + "Applied " + percent + "% women discount to " + length + " invoice(s):" + ANSI.format(ANSI.CLEAR));
            result.forEach(x -> System.out.println(
                String.format("%s% 5d) %s%s", ANSI.format(ANSI.FG_DARK_GRAY), ++counter, ANSI.format(ANSI.CLEAR), x.toString()))
            );
        } else {
            System.out.println(ANSI.format(ANSI.BOLD, ANSI.UNDERLINE, ANSI.FG_LIGHT_RED) + "Found no invoice to apply" + ANSI.format(ANSI.CLEAR));
        }
    }
}
