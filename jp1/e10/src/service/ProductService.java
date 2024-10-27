package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import entity.Product;
import igeneric.IGenericService;

public class ProductService implements IGenericService<Product, String> {
    public List<Product> list;

    public ProductService() {
        list = new ArrayList<>();
    }

    @Override
    public void add(Product item) {
        list.add(item);
        
    }

    @Override
    public Optional<Product> get(String id) {
        return list.stream().filter(x -> x.getId() == id).findFirst();
    }
}
