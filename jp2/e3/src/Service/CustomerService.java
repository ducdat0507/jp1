package Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import Entity.Customer;
import IGeneral.IGenericService;

public class CustomerService implements IGenericService<Customer> {
    private Set<Customer> set;

    public CustomerService() {
        set = new HashSet<>();
    }

    @Override
    public void add(Customer item) {
        set.add(item);
    }
    @Override
    public void remove(Customer item) {
        set.remove(item);
    }
    @Override
    public Customer get(String id) {
        return set.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
    }
    @Override
    public Stream<Customer> stream() {
        return set.stream();
    }
}
