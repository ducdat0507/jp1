package Controller;

import java.util.stream.Stream;

import Entity.Customer;
import IGeneral.IGenericController;
import Service.CustomerService;

public class CustomerController implements IGenericController<Customer> {
    private CustomerService service;

    public CustomerController() {
        service = new CustomerService();
    }

    @Override
    public void add(Customer item) {
        service.add(item);
    }
    @Override
    public Customer get(String id) {
        return service.get(id);
    }
    @Override
    public void remove(Customer item) {
        service.remove(item);
    }
    @Override
    public Stream<Customer> stream() {
        return service.stream();
    }
}
