package Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import Entity.Booking;
import IGeneral.IGenericService;

public class BookingService implements IGenericService<Booking> {
    private Set<Booking> set;
    public int nextId;

    public BookingService() {
        set = new HashSet<>();
        nextId = 1;
    }

    @Override
    public void add(Booking item) {
        if (item.getId() == null) {
            item.setId(Integer.toString(nextId++));
        }
        set.add(item);
    }
    @Override
    public void remove(Booking item) {
        set.remove(item);
    }
    @Override
    public Booking get(String id) {
        return set.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
    }
    @Override
    public Stream<Booking> stream() {
        return set.stream();
    }
}
