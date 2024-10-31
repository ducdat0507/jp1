package Template.Entity;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import Template.IGenericService;

public class EntityService<T extends Entity> implements IGenericService<T> {
    private Set<T> set;

    public EntityService() {
        set = new HashSet<>();
    }

    @Override
    public void add (T item) {
        if (item.getId() == null) item.setId(autoIncrement(item));
        set.add(item);
    }
    @Override
    public void remove (T item) {
        set.remove(item);
    }
    @Override
    public T get (String id) {
        return set.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
    }
    @Override
    public Stream<T> stream() {
        return set.stream();
    }

    protected String autoIncrement(T item) {
        throw new UnsupportedOperationException("Auto increment function not implemented");
    }
}
