package controller;

import entity.Account;
import global.ANSI;
import igeneric.IUpdateController;
import service.AccountService;

public class AccountUpdateController implements IUpdateController {
    private AccountService service;
    public AccountUpdateController(AccountService service) {
        this.service = service;
    }

    public void promptAndUpdate() {
        String input;
        int id;
        Account account;

        while (true) {
            System.out.print("Enter account ID (leave blank to cancel): " + ANSI.format(ANSI.FG_LIGHT_YELLOW));
            input = System.console().readLine().trim();
            System.out.print(ANSI.format(ANSI.CLEAR));

            if (input == "") return;

            try {
                id = Integer.parseInt(input);
            } catch (NumberFormatException e) {   
                System.out.println(ANSI.format(ANSI.FG_LIGHT_RED) + "Not a valid number" + ANSI.format(ANSI.CLEAR));
                continue;
            }   

            account = service.get(id);

            if (account == null) {   
                System.out.println(ANSI.format(ANSI.FG_LIGHT_RED) + "Account not found" + ANSI.format(ANSI.CLEAR));
                continue;
            }   

            break;
        }

        System.out.println("Editing: " + ANSI.format(ANSI.BOLD) + account + ANSI.format(ANSI.CLEAR));
        System.out.println("Leave blank to use current value in (parentheses)");

        // Balance
        while (true) {
            System.out.print("Enter account balance (" + account.getBalance() + ") [must be greater than 0]: " + ANSI.format(ANSI.FG_LIGHT_YELLOW));
            input = System.console().readLine().trim();
            System.out.print(ANSI.format(ANSI.CLEAR));

            if (input == "") break;

            double value;
            try {
                value = Double.parseDouble(input);
            } catch (NumberFormatException e) {   
                System.out.println(ANSI.format(ANSI.FG_LIGHT_RED) + "Not a valid number" + ANSI.format(ANSI.CLEAR));
                continue;
            }   

            if (value < 0) {   
                System.out.println(ANSI.format(ANSI.FG_LIGHT_RED) + "Value can not be smaller than 0" + ANSI.format(ANSI.CLEAR));
                continue;
            }   

            account.setBalance(value);
            break;
        }
        
        System.out.println("Updated: " + ANSI.format(ANSI.BOLD) + account + ANSI.format(ANSI.CLEAR));
    }
}
