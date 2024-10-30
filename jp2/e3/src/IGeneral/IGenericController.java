package IGeneral;

import java.util.stream.Stream;

public interface IGenericController<T> {
    public void add(T item);
    public void remove(T item);
    public T get(String id);
    public Stream<T> stream();
}
