package Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import Entity.Customer;
import IGeneric.IManager;
import Service.CustomerService;

public class CustomerController implements IManager<Customer> {

    private CustomerService service;

    public CustomerController() {
        this.service = new CustomerService();
    }
    public CustomerController(List<Customer> customers) {
        this.service = new CustomerService(customers);
    }

    @Override
    public Optional<Customer> getById(int id) {
        return service.getById(id);
    }

    @Override
    public Optional<Customer> getByCode(String code) {
        return service.getByCode(code);
    }

    @Override
    public List<Customer> queryByName(String query) {
        return service.queryByName(query);
    }

}
