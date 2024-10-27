import java.util.ArrayList;
import java.util.List;

import Controller.CustomerController;
import Entity.Customer;

public class Main {
    public static void main(String[] args) {
        List<Customer> customers = new ArrayList<>();
        CustomerController controller = new CustomerController(customers);

        customers.add(new Customer(1, "CS001", "ajfkajsdlajdjddjklsdjaskldjdkljasd"));
        customers.add(new Customer(2, "CS002", "ewgfqwfjfjankvnkljdasjfkljsvsvwfle"));
        customers.add(new Customer(3, "CS003", "hswdfksnvlzjgsovpsnczpdnlovlsjbkwng"));

        controller.getById(1).ifPresentOrElse(System.out::println, () -> System.out.println("Not found"));
    }
}
