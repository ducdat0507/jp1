package service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import entity.Account;
import global.Global;
import igeneric.IContainerService;
import igeneric.ISearchService;

public class AccountService implements IContainerService<Account>, ISearchService<Account> {
    List<Account> list;

    public AccountService() {
        list = new ArrayList<>();
    }

    @Override
    public void add(Account item) {
        list.add(item);
    }

    @Override
    public Account get(int id) {
        return list.stream().filter(x -> x.getId() == id).findAny().orElse(null);
    }

    @Override
    public List<Account> list(int limit, int offset) {
        return list.stream().limit(limit).skip(offset).toList();
    }

    @Override
    public List<Account> list(Comparator<? super Account> sort, int limit, int offset) {
        return list.stream().sorted(sort).limit(limit).skip(offset).toList();
    }

    @Override
    public List<Account> search(String query) {
        List<Account> result = new ArrayList<>();
        try {
            int id = Integer.parseInt(query);
            Optional.ofNullable(get(id)).ifPresent(result::add);
        } catch (NumberFormatException e) { /* Do nothing */ }

        list.stream().filter(Global.fuzzyMatch(query, Account::getCustomerName)).forEach(result::add);

        return result;
    }
}
