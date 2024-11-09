package IGeneral.Entity;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import IGeneral.IGenericService;

public class EntityService<T extends Entity> implements IGenericService<T> {
    private Set<T> set;
    protected long newID;

    public EntityService() {
        set = new HashSet<>();
    }

    @Override
    public void add (T item) {
        if (item.getId() == 0) item.setId(autoIncrement(item));
        set.add(item);
    }
    @Override
    public void remove (T item) {
        set.remove(item);
    }
    @Override
    public T get (long id) {
        return set.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }
    @Override
    public Stream<T> stream() {
        return set.stream();
    }

    protected long autoIncrement(T item) {
        return ++newID;
    }
}
