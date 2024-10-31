package Template;

import java.util.stream.Stream;

public interface IGenericController<T> {
    public void add(T item);
    public void add(T[] items);
    public void add(Iterable<T> items);
    public void remove(T item);
    public void remove(T[] items);
    public void remove(Iterable<T> items);
    public T get(String id);
    public Stream<T> stream();
}
