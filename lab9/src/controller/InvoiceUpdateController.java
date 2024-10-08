package controller;

import entity.Invoice;
import global.ANSI;
import igeneric.IUpdateController;
import service.InvoiceService;

public class InvoiceUpdateController implements IUpdateController {
    private InvoiceService service;
    public InvoiceUpdateController(InvoiceService service) {
        this.service = service;
    }

    public void promptAndUpdate() {
        String input;
        int id;
        Invoice invoice;

        while (true) {
            System.out.print("Enter invoice ID (leave blank to cancel): " + ANSI.format(ANSI.FG_LIGHT_YELLOW));
            input = System.console().readLine().trim();
            System.out.print(ANSI.format(ANSI.CLEAR));

            if (input == "") return;

            try {
                id = Integer.parseInt(input);
            } catch (NumberFormatException e) {   
                System.out.println(ANSI.format(ANSI.FG_LIGHT_RED) + "Not a valid number" + ANSI.format(ANSI.CLEAR));
                continue;
            }   

            invoice = service.get(id);

            if (invoice == null) {   
                System.out.println(ANSI.format(ANSI.FG_LIGHT_RED) + "Invoice not found" + ANSI.format(ANSI.CLEAR));
                continue;
            }   

            break;
        }

        System.out.println("Editing: " + ANSI.format(ANSI.BOLD) + invoice + ANSI.format(ANSI.CLEAR));
        System.out.println("Leave blank to use current value in (parentheses)");

        // Amount
        while (true) {
            System.out.print("Enter invoice amount (" + invoice.getAmount() + ") [must be greater than 0]: " + ANSI.format(ANSI.FG_LIGHT_YELLOW));
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

            if (value >= 0) {   
                System.out.println(ANSI.format(ANSI.FG_LIGHT_RED) + "Value must be greater than 0" + ANSI.format(ANSI.CLEAR));
                continue;
            }   

            invoice.setAmount(value);
            break;
        }
        
        System.out.println("Updated: " + ANSI.format(ANSI.BOLD) + invoice + ANSI.format(ANSI.CLEAR));
    }
}
