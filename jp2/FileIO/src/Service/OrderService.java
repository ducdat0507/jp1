package Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;

import Entity.Order;
import IGeneral.Entity.EntityService;

public class OrderService extends EntityService<Order> {
    public OrderService() {
        super();
        try {
            BufferedReader reader = new BufferedReader(
                new FileReader(new File(System.getProperty("user.dir"), "data/order.in.txt"))
            );
            reader.lines().forEach(x -> {
                String[] props = x.split(";");
                this.add(new Order(
                    Long.parseLong(props[0]),
                    Long.parseLong(props[1]),
                    LocalDateTime.parse(props[2]),
                    props[3].isEmpty() ? null : LocalDateTime.parse(props[3])
                ));
            });
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
