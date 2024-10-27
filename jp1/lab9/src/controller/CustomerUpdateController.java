package controller;

import entity.Customer;
import entity.Gender;
import global.ANSI;
import igeneric.IUpdateController;
import service.CustomerService;

public class CustomerUpdateController implements IUpdateController {
    private CustomerService service;
    public CustomerUpdateController(CustomerService service) {
        this.service = service;
    }

    public void promptAndUpdate() {
        String input;
        int id;
        Customer customer;

        while (true) {
            System.out.print("Enter customer ID (leave blank to cancel): " + ANSI.format(ANSI.FG_LIGHT_YELLOW));
            input = System.console().readLine().trim();
            System.out.print(ANSI.format(ANSI.CLEAR));

            if (input == "") return;

            try {
                id = Integer.parseInt(input);
            } catch (NumberFormatException e) {   
                System.out.println(ANSI.format(ANSI.FG_LIGHT_RED) + "Not a valid number" + ANSI.format(ANSI.CLEAR));
                continue;
            }   

            customer = service.get(id);

            if (customer == null) {   
                System.out.println(ANSI.format(ANSI.FG_LIGHT_RED) + "Customer not found" + ANSI.format(ANSI.CLEAR));
                continue;
            }   

            break;
        }

        System.out.println("Editing: " + ANSI.format(ANSI.BOLD) + customer + ANSI.format(ANSI.CLEAR));
        System.out.println("Leave blank to use current value in (parentheses)");

        // Name
        while (true) {
            System.out.print("Enter customer name (" + customer.getName() + "): " + ANSI.format(ANSI.FG_LIGHT_YELLOW));
            input = System.console().readLine().trim();
            System.out.print(ANSI.format(ANSI.CLEAR));

            if (input == "") break;

            customer.setName(input);

            break;
        }

        // Gender
        while (true) {
            System.out.print("Enter customer gender (" + customer.getGender().getLabel() + ") [Male/Female/Other/Unknown]: " + ANSI.format(ANSI.FG_LIGHT_YELLOW));
            input = System.console().readLine().trim();
            System.out.print(ANSI.format(ANSI.CLEAR));

            if (input == "") break;

            switch (input.toLowerCase()) {
                case "m": case "male":
                    customer.setGender(Gender.MALE);
                    break;
                case "f": case "female":
                    customer.setGender(Gender.FEMALE);
                    break;
                case "o": case "other":
                    customer.setGender(Gender.OTHER);
                    break;
                case "u": case "unknown":
                    customer.setGender(Gender.UNKNOWN);
                    break;
            
                default:
                    System.out.println(ANSI.format(ANSI.FG_LIGHT_RED) + "Invalid choice" + ANSI.format(ANSI.CLEAR));
                    break;
            }

            break;
        }

        // Discount
        while (true) {
            System.out.print("Enter customer discount (" + customer.getDiscount() + ") [must be in 0~100 range]: " + ANSI.format(ANSI.FG_LIGHT_YELLOW));
            input = System.console().readLine().trim();
            System.out.print(ANSI.format(ANSI.CLEAR));

            if (input == "") break;

            double value;
            try {
                value = Double.parseDouble(input);
            } catch (NumberFormatException e) {   
                System.out.println(ANSI.format(ANSI.FG_LIGHT_RED) + "Not a valid number" + ANSI.format(ANSI.CLEAR));
                continue;
            }   

            if (value < 0) {   
                System.out.println(ANSI.format(ANSI.FG_LIGHT_RED) + "Value can not be smaller than 0" + ANSI.format(ANSI.CLEAR));
                continue;
            }   
            if (value >= 100) {   
                System.out.println(ANSI.format(ANSI.FG_LIGHT_RED) + "Value must be less than 100" + ANSI.format(ANSI.CLEAR));
                continue;
            }   

            customer.setDiscount(value);
            break;
        }
        
        System.out.println("Updated: " + ANSI.format(ANSI.BOLD) + customer + ANSI.format(ANSI.CLEAR));
    }
}
