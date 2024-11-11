package Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;

import Entity.Product;
import IGeneral.Entity.EntityService;

public class ProductService extends EntityService<Product> {
    public ProductService() {
        super();
        try {
            BufferedReader reader = new BufferedReader(
                new FileReader(new File(System.getProperty("user.dir"), "data/product.in.txt"))
            );
            reader.lines().forEach(x -> {
                String[] props = x.split(";");
                this.add(new Product(
                    Long.parseLong(props[0]),
                    props[1],
                    Double.parseDouble(props[2]),
                    Integer.parseInt(props[3])
                ));
            });
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
