package Template;

import java.util.stream.Stream;

public interface IGenericService<T> {
    public void add(T item);
    public void remove(T item);
    public T get(String id);
    public Stream<T> stream();
}
