package entity;

import exception.TemperatureException;
import exception.TooColdException;
import exception.UnusualTasteException;

public class VirtualCafe {
    public static void serveCustomer(VirtualPerson cust, CoffeeCup cup) {
        try {
            cust.drinkCoffee(cup);
            System.out.println("Coffee tastes just right.");
        } catch (UnusualTasteException e) {
            System.out.println("Customer is complaining of an unusual taste.");
            String s = e.getMessage();
            if (s != null) {
                System.out.println(s);
            }
        }
    }
}
