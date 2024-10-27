package controller;

import entity.Account;
import service.TransactionService;
import service.ValidationService;

public class AccountController extends TransactionService {
    private Account account;

    public AccountController(Account account) {
        this.account = account;
    }

    public void depositString(String amount) {
        try {
            double num = ValidationService.validateNumber(amount);
            deposit(num);
        } catch (Error e) {/* */}
    }
    @Override
    public void deposit(double amount) {
        if (ValidationService.validateDeposit(account, amount))
            account.setBalance(account.getBalance() + amount);
    }


    public void withdrawString(String amount) {
        try {
            double num = ValidationService.validateNumber(amount);
            deposit(num);
        } catch (Error e) {/* */}
    }
    @Override
    public void withdraw(double amount) {
        if (ValidationService.validateWithdraw(account, amount))
            account.setBalance(account.getBalance() - amount);
    }
}
