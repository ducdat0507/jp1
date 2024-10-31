package Controller;

import java.util.stream.Stream;

import Entity.Room;
import IGeneral.IGenericController;
import Service.RoomService;

public class RoomController implements IGenericController<Room> {
    private RoomService service;

    public RoomController() {
        service = new RoomService();
    }

    @Override
    public void add(Room item) {
        service.add(item);
    }
    @Override
    public Room get(String id) {
        return service.get(id);
    }
    @Override
    public void remove(Room item) {
        service.remove(item);
    }
    @Override
    public Stream<Room> stream() {
        return service.stream();
    }
}
