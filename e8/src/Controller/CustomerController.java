package Controller;

import java.util.List;
import java.util.Optional;

import Entity.Customer;
import Service.CustomerService;

public class CustomerController {
    private CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    public Optional<Customer> getById(int id){
        return service.getById(id);
    }
    public Optional<Customer> getByCode(String code){
        return service.getByCode(code);
    }
    public List<Customer> getByName(String keyword) {
        return service.getByName(keyword);
    }
}
