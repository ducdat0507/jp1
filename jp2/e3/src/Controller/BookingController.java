package Controller;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Entity.Booking;
import Entity.Customer;
import Entity.RoomType;
import Global.Global;
import IGeneral.IGenericController;
import Service.BookingService;

public class BookingController implements IGenericController<Booking> {
    private BookingService service;

    public BookingController() {
        service = new BookingService();
    }

    @Override
    public void add(Booking item) {
        service.add(item);
    }
    @Override
    public Booking get(String id) {
        return service.get(id);
    }
    @Override
    public void remove(Booking item) {
        service.remove(item);
    }
    @Override
    public Stream<Booking> stream() {
        return service.stream();
    }

    public Set<Booking> search(String query) {
        var fm = Global.fuzzyMatch(query);
        return stream().filter(x -> (
            fm.test(x.getRoom().getId()) || fm.test(x.getCustomer().getName()) || fm.test(x.getCustomer().getPhone())
        )).collect(Collectors.toSet());
    }
}
