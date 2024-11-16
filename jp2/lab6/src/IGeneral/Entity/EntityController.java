package IGeneral.Entity;

import java.util.stream.Stream;

import IGeneral.IGenericController;

public class EntityController<S extends EntityService<T>, T extends Entity> implements IGenericController<T> {
    protected S service;

    public EntityController(S service) {
        this.service = service;
    }

    @Override
    public void add(T item) {
        service.add(item);
    }
    @Override
    public void add(T[] items) {
        for (T item : items) service.add(item);
    }
    @Override
    public void add(Iterable<T> items) {
        for (T item : items) service.add(item);
    }
    @Override
    public void remove(T item) {
        service.remove(item);
    }
    @Override
    public void remove(T[] items) {
        for (T item : items) service.add(item);
    }
    @Override
    public void remove(Iterable<T> items) {
        for (T item : items) service.add(item);
    }
    @Override
    public T get(long id) {
        return service.get(id);
    }
    @Override
    public Stream<T> stream() {
        return service.stream();
    }

    
}
