package Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import Entity.Customer;
import IGeneric.IManager;

public class CustomerService implements IManager<Customer> {

    private List<Customer> customers;

    public CustomerService() {
        this.customers = new ArrayList<>();
    }
    public CustomerService(List<Customer> customers) {
        this.customers = customers;
    }


    @Override
    public Optional<Customer> getById(int id) {
        return customers.stream().filter(x -> x.getId() == id).findAny();
    }

    @Override
    public Optional<Customer> getByCode(String code) {
        return customers.stream().filter(x -> x.getCode().equals(code)).findAny();
    }

    @Override
    public List<Customer> queryByName(String query) {
        Pattern pattern = Pattern.compile(Pattern.quote(query), Pattern.CASE_INSENSITIVE);
        return customers.stream().filter(x -> pattern.matcher(x.getName()).find()).collect(Collectors.toList());
    }

}
