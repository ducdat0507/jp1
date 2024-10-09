import java.time.LocalDateTime;
import java.util.zip.CRC32;

import controller.MainController;
import entity.Customer;
import entity.Order;
import entity.OrderDetail;
import entity.Product;
import exception.InvalidArgumentException;
import exception.NotEnoughInventoryException;

public class Main {
    public static void main(String[] args) {
        MainController controller = new MainController();

        try {
            controller.addProduct(new Product("MS000001", "Asfskdjdkjd", 20));
            controller.addProduct(new Product("NE000002", "Abcdefghijklmn", 20));
            controller.addProduct(new Product("SE000003", "Ad aSd asfasf", 20));
            controller.addProduct(new Product("error", "not valid", 20));
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }
        try {
            controller.addCustomer(new Customer(1, "Abcabc"));
            controller.addCustomer(new Customer(2, "Bcabca"));
            controller.addCustomer(new Customer(3, "Not a valid name 01234556"));
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }
        try {
            controller.addOrder(new Order("ORDPM00000001", controller.getCustomer(1), LocalDateTime.now()));
            controller.addOrder(new Order("bla bla", controller.getCustomer(2), LocalDateTime.now()));
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }
        try {
            controller.addOrderDetail(new OrderDetail(1, controller.getOrder("ORDPM00000001"), controller.getProduct("MS000001"), 6));
            controller.addOrderDetail(new OrderDetail(2, controller.getOrder("ORDPM00000001"), controller.getProduct("MS000001"), 6));
            controller.addOrderDetail(new OrderDetail(3, controller.getOrder("ORDPM00000001"), controller.getProduct("MS000001"), 6));
            controller.addOrderDetail(new OrderDetail(4, controller.getOrder("ORDPM00000001"), controller.getProduct("MS000001"), 6));
        } catch (InvalidArgumentException | NotEnoughInventoryException e) {
            e.printStackTrace();
        }
    }
}
