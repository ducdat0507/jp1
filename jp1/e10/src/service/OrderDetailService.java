package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import entity.OrderDetail;
import igeneric.IGenericService;

public class OrderDetailService implements IGenericService<OrderDetail, Integer> {
    public List<OrderDetail> list;

    public OrderDetailService() {
        list = new ArrayList<>();
    }

    @Override
    public void add(OrderDetail item) {
        list.add(item);
        
    }
    
    @Override
    public Optional<OrderDetail> get(Integer id) {
        return list.stream().filter(x -> x.getId() == id).findFirst();
    }
}
