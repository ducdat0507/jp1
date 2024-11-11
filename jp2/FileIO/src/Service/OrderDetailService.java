package Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;

import Entity.OrderDetail;
import IGeneral.Entity.EntityService;

public class OrderDetailService extends EntityService<OrderDetail> {
    public OrderDetailService() {
        super();
        try {
            BufferedReader reader = new BufferedReader(
                new FileReader(new File(System.getProperty("user.dir"), "data/orderdetail.in.txt"))
            );
            reader.lines().forEach(x -> {
                String[] props = x.split(";");
                this.add(new OrderDetail(
                    Long.parseLong(props[0]),
                    Long.parseLong(props[1]),
                    Long.parseLong(props[2]),
                    Integer.parseInt(props[3]),
                    Double.parseDouble(props[4])
                ));
            });
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
