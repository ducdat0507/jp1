package Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import Entity.Room;
import IGeneral.IGenericService;

public class RoomService implements IGenericService<Room> {
    private Set<Room> set;

    public RoomService() {
        set = new HashSet<>();
    }

    @Override
    public void add(Room item) {
        set.add(item);
    }
    @Override
    public void remove(Room item) {
        set.remove(item);
    }
    @Override
    public Room get(String id) {
        return set.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
    }
    @Override
    public Stream<Room> stream() {
        return set.stream();
    }
}
