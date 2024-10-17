package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import entity.Customer;
import entity.Order;
import igeneric.IGenericService;

public class OrderService implements IGenericService<Order, String> {
    public List<Order> list;

    public OrderService() {
        list = new ArrayList<>();
    }

    @Override
    public void add(Order item) {
        list.add(item);
        
    }
    
    @Override
    public Optional<Order> get(String id) {
        return list.stream().filter(x -> x.getId() == id).findFirst();
    }

    @Override
    public void delete (Order item) {
        list.remove(item);
    }
}
