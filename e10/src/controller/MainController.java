package controller;

import entity.Customer;
import entity.Order;
import entity.OrderDetail;
import entity.Product;
import exception.NotEnoughInventoryException;
import service.CustomerService;
import service.OrderDetailService;
import service.OrderService;
import service.ProductService;

public class MainController {
    private ProductService productService;
    private CustomerService customerService;
    private OrderService orderService;
    private OrderDetailService orderDetailService;

    public MainController() {
        productService = new ProductService();
        customerService = new CustomerService();
        orderService = new OrderService();
        orderDetailService = new OrderDetailService();
    }

    public void addProduct(Product product) {
        productService.add(product);
    }
    public void addCustomer(Customer customer) {
        customerService.add(customer);
    }
    public void addOrder(Order order) {
        orderService.add(order);
    }
    public void addOrderDetail(OrderDetail orderDetail) throws NotEnoughInventoryException {
        int amtInStock = orderDetail.getProduct().getQuantity();
        if (amtInStock < orderDetail.getQuantity())
            throw new NotEnoughInventoryException("Not enough quantity in stock to add this order");
        try { orderDetail.getProduct().setQuantity(amtInStock - orderDetail.getQuantity()); } catch (Throwable e) {}
        orderDetailService.add(orderDetail);
    }

    public Product getProduct(String id) {
        return productService.get(id).orElse(null);
    }
    public Customer getCustomer(int id) {
        return customerService.get(id).orElse(null);
    }
    public Order getOrder(String id) {
        return orderService.get(id).orElse(null);
    }
    public OrderDetail getOrderDetail(int id) {
        return orderDetailService.get(id).orElse(null);
    }
}
