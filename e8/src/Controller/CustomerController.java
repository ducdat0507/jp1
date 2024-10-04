package Controller;

import java.util.Optional;

import Entity.Customer;
import Service.CustomerService;

public class CustomerController {
    private CustomerService cs;
    public CustomerController(CustomerService cs) {
        this.cs = cs;
    }
    public Optional<Customer> getById(int id){
        return cs.getById(id);
    }
}
