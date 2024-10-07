package controller;

import entity.Customer;
import igeneric.ISearchController;
import igeneric.ISearchService;
import service.CustomerService;

public class BankingController {
    private CustomerService customerService;

    public BankingController() {
        customerService = new CustomerService();
    }

    public void addCustomer(Customer customer) {
        customerService.add(customer);
    }

    public void doSearch() {
        ISearchController searchController;
        searchController = new SearchController<Customer>(customerService);
        searchController.promptAndSearch();
    }
}
