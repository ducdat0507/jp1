import entity.CoffeeCup;
import entity.VirtualCafe;
import entity.VirtualPerson;
import exception.UnusualTasteException;

public class Main {
    public static void main(String[] args) {
        CoffeeCup cup = new CoffeeCup();
        try {
            VirtualPerson cust = new VirtualPerson();
            VirtualCafe.serveCustomer(cust, cup);
        } catch (UnusualTasteException e) {
            System.out.println("This coffee has an unusual taste.");
            System.out.println(e.toString());
        }
    }
}
