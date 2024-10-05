package Controller;

import java.util.List;
import java.util.Optional;

import Entity.Account;
import Service.AccountService;

public class AccountController {
    private AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    public List<Account> getByCustomerCode(String code) {
        return service.getByCustomerCode(code);
    }
    public List<Account> getByCustomerName(String keyword) {
        return service.getByCustomerName(keyword);
    }
    public List<Account> getAccountByCustomer() {
        return service.getAccountByCustomer();
    }
    public long countAccountByCustomer() {
        return service.countAccountByCustomer();
    }
    public Optional<Account> getMaxBalance() {
        return service.getMaxBalance();
    }

}
