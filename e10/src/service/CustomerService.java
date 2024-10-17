package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import entity.Customer;
import igeneric.IGenericService;

public class CustomerService implements IGenericService<Customer, Integer> {
    public List<Customer> list;

    public CustomerService() {
        list = new ArrayList<>();
    }

    @Override
    public void add(Customer item) {
        list.add(item);
        
    }

    @Override
    public Optional<Customer> get(Integer id) {
        return list.stream().filter(x -> x.getId() == id).findFirst();
    }

    @Override
    public void delete (Customer item) {
        list.remove(item);
    }
}
