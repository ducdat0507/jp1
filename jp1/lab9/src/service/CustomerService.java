package service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import entity.Account;
import entity.Customer;
import global.Global;
import igeneric.IContainerService;
import igeneric.ISearchService;

public class CustomerService implements IContainerService<Customer>, ISearchService<Customer> {
    List<Customer> list;

    public CustomerService() {
        list = new ArrayList<>();
    }

    @Override
    public void add(Customer item) {
        list.add(item);
    }

    @Override
    public Customer get(int id) {
        return list.stream().filter(x -> x.getId() == id).findAny().orElse(null);
    }

    @Override
    public List<Customer> list(int limit, int offset) {
        return list.stream().limit(limit).skip(offset).toList();
    }

    @Override
    public List<Customer> list(Comparator<? super Customer> sort, int limit, int offset) {
        return list.stream().sorted(sort).limit(limit).skip(offset).toList();
    }

    @Override
    public List<Customer> search(String query) {
        List<Customer> result = new ArrayList<>();
        try {
            int id = Integer.parseInt(query);
            Optional.ofNullable(get(id)).ifPresent(result::add);
        } catch (NumberFormatException e) { /* Do nothing */ }

        list.stream().filter(Global.fuzzyMatch(query, Customer::getName)).forEach(result::add);

        return result;
    }
}
