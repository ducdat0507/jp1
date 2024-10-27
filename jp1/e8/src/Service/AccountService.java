package Service;

import Entity.Account;
import Entity.Customer;
import Entity.Gender;
import Entity.Staff;
import Global.Global;
import IGeneric.IGeneral;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AccountService {
    private List<Account> accounts;
    public AccountService(List<Account> accounts){
        this.accounts = accounts;
    }
    
    public List<Account> getByCustomerCode(String code) {
        return accounts.stream()
            .filter(a->a.getCustomer().getCode().equalsIgnoreCase(code))
            .collect(Collectors.toList());
    }

    public List<Account> getByCustomerName(String keyword) {
        return accounts.stream()
            .filter(a->Global.ignoreCase(keyword, a.getCustomer().getName()))
            .collect(Collectors.toList());
    }

    public List<Account> getAccountByCustomer() {
        return accounts.stream()
            .filter(a->a.getBalance() > 1e6 && a.getCustomer().getGender() == Gender.MALE)
            .collect(Collectors.toList());
    }

    public long countAccountByCustomer() {
        return accounts.stream()
            .filter(a->a.getBalance() > 1e5 && a.getCustomer().getGender() == Gender.FEMALE)
            .count();
    }

    public Optional<Account> getMaxBalance() {
        return accounts.stream()
            .filter(a-> a.getCustomer().getGender() == Gender.FEMALE)
            .max(Comparator.comparing(Account::getBalance));
    }

}
