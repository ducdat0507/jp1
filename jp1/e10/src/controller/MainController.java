package controller;

import entity.Customer;
import entity.Order;
import entity.OrderDetail;
import entity.Product;
import exception.ValidationException;
import exception.NotEnoughInventoryException;
import service.CustomerService;
import service.MainService;
import service.OrderDetailService;
import service.OrderService;
import service.ProductService;

public class MainController {
    private MainService service;

    public MainController() {
        service = new MainService();
    }

    public void addProduct(Product product) {
        try { service.addProduct(product); }
        catch (ValidationException e) { e.printStackTrace(); }
    }
    public void addCustomer(Customer customer) {
        try { service.addCustomer(customer); }
        catch (ValidationException e) { e.printStackTrace(); }
    }
    public void addOrder(Order order) {
        try { service.addOrder(order); }
        catch (ValidationException e) { e.printStackTrace(); }
    }
    public void addOrderDetail(OrderDetail orderDetail) {
        try { service.addOrderDetail(orderDetail); }
        catch (ValidationException | NotEnoughInventoryException e) { e.printStackTrace(); }
    }

    public Product getProduct(String id) {
        return service.getProduct(id);
    }
    public Customer getCustomer(int id) {
        return service.getCustomer(id);
    }
    public Order getOrder(String id) {
        return service.getOrder(id);
    }
    public OrderDetail getOrderDetail(int id) {
        return service.getOrderDetail(id);
    }
}
