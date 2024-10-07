import controller.BankingController;
import entity.Customer;
import entity.Gender;

public class Main {
    public static void main(String[] args) {
        BankingController controller = new BankingController();
        controller.addCustomer(new Customer(1, "John", Gender.MALE));
        controller.addCustomer(new Customer(2, "Dave", Gender.MALE));
        controller.addCustomer(new Customer(3, "Roxy", Gender.FEMALE));
        controller.addCustomer(new Customer(4, "Jade", Gender.FEMALE));

        controller.doSearch();
    }
}
