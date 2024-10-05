package Service;

import Entity.Customer;
import Global.Global;
import IGeneric.IGeneral;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomerService implements IGeneral<Customer> {
    private List<Customer> customers;
    public CustomerService(List<Customer> customers){
        this.customers = customers;
    }
    @Override
    public Optional<Customer> getById(int id) {
        return customers.stream()
            .filter(c->c.getId()==id)
            .findFirst();
    }
    @Override
    public Optional<Customer> getByCode(String code) {
        return customers.stream()
            .filter(c->c.getCode().equalsIgnoreCase(code))
            .findFirst();
    }

    @Override
    public List<Customer> getByName(String keyword) {
        return customers.stream()
            .filter(c->Global.ignoreCase(keyword, c.getName()))
            .collect(Collectors.toList());
    }
}
