
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import Entity.Order;
import Entity.OrderDetail;
import Service.CustomerService;
import Service.OrderDetailService;
import Service.OrderService;
import Service.ProductService;

public class Main {
    public static void main(String[] args) {
        CustomerService customers = new CustomerService();
        ProductService products = new ProductService();
        OrderService orders = new OrderService();
        OrderDetailService orderDetails = new OrderDetailService();

        long customerId = 1;
        Set<Long> customerOrders = orders.stream()
            .filter(x -> x.getCustomerId() == customerId)
            .map(x -> x.getId())
            .collect(Collectors.toSet());
        Set<OrderDetail> customerOrderDetails = orderDetails.stream()
            .filter(x -> customerOrders.contains(x.getOrderId()))
            .collect(Collectors.toSet());

        try {
            BufferedWriter writer = new BufferedWriter(
                new FileWriter(new File(System.getProperty("user.dir"), "data/order.in.txt"))
            );
            customerOrderDetails.forEach(x -> {
                try {
                    writer.write(x.toString());
                    writer.newLine();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}