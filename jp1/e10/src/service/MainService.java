package service;

import entity.Customer;
import entity.Order;
import entity.OrderDetail;
import entity.Product;
import exception.CustomerIdNotFoundException;
import exception.InvalidQuantityException;
import exception.NotEnoughInventoryException;
import exception.OrderIdNotFoundException;
import exception.ProductIdNotFoundException;
import exception.ValidationException;
import validator.CustomerValidator;
import validator.OrderDetailValidator;
import validator.OrderValidator;
import validator.ProductValidator;

public class MainService {
    private ProductService productService;
    private CustomerService customerService;
    private OrderService orderService;
    private OrderDetailService orderDetailService;

    public MainService() {
        productService = new ProductService();
        customerService = new CustomerService();
        orderService = new OrderService();
        orderDetailService = new OrderDetailService();
    }

    public void addProduct(Product product) throws ValidationException {
        ProductValidator.validate(product);
        productService.add(product);
    }
    public void addCustomer(Customer customer) throws ValidationException {
        CustomerValidator.validate(customer);
        customerService.add(customer);
    }
    public void addOrder(Order order) throws ValidationException {
        OrderValidator.validate(order);

        customerService.get(order.getCustomerId())
            .orElseThrow(() -> new CustomerIdNotFoundException("Customer does not exist in database"));
        orderService.add(order);
    }
    public void addOrderDetail(OrderDetail orderDetail) throws NotEnoughInventoryException, ValidationException {
        OrderDetailValidator.validate(orderDetail);

        orderService.get(orderDetail.getOrderId())
            .orElseThrow(() -> new OrderIdNotFoundException("Order does not exist in database"));
        Product product = productService.get(orderDetail.getProductId())
            .orElseThrow(() -> new ProductIdNotFoundException("Product does not exist in database"));

        int amtInStock = product.getQuantity();
        if (amtInStock < orderDetail.getQuantity())
            throw new NotEnoughInventoryException("Not enough quantity in stock to add this order");
        product.setQuantity(amtInStock - orderDetail.getQuantity());

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
