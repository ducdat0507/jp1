package Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import Entity.Customer;
import IGeneral.Entity.EntityService;

public class CustomerService extends EntityService<Customer> {
    public CustomerService() {
        super();
        try {
            BufferedReader reader = new BufferedReader(
                new FileReader(new File(System.getProperty("user.dir"), "data/customer.in.txt"))
            );
            reader.lines().forEach(x -> {
                String[] props = x.split(";");
                this.add(new Customer(
                    Long.parseLong(props[0]),
                    props[1]
                ));
            });
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
