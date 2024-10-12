import java.time.LocalDateTime;
import java.util.zip.CRC32;

import controller.MainController;
import entity.Customer;
import entity.Order;
import entity.OrderDetail;
import entity.Product;
import exception.ValidationException;
import exception.NotEnoughInventoryException;

public class Main {
    public static void main(String[] args) {
        MainController controller = new MainController();

        controller.addProduct(new Product("MS000001", "Asfskdjdkjd", 20));
        controller.addProduct(new Product("NE000002", "Abcdefghijklmn", 20));
        controller.addProduct(new Product("SE000003", "Ad aSd asfasf", 20));
        controller.addProduct(new Product("error", "not valid", 20));
        controller.addCustomer(new Customer(1, "Abcabc"));
        controller.addCustomer(new Customer(2, "Bcabca"));
        controller.addCustomer(new Customer(3, "Not a valid name 01234556"));
        controller.addOrder(new Order("ORDPM00000001", 1, LocalDateTime.now()));
        controller.addOrder(new Order("bla bla", 2, LocalDateTime.now()));
        controller.addOrderDetail(new OrderDetail(1,"ORDPM00000001", "MS000001", 6));
        controller.addOrderDetail(new OrderDetail(2,"ORDPM00000001", "MS000001", 6));
        controller.addOrderDetail(new OrderDetail(3,"ORDPM00000001", "MS000001", 6));
        controller.addOrderDetail(new OrderDetail(4,"ORDPM00000001", "MS000001", 6));
    }
}
