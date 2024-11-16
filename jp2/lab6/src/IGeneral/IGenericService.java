package IGeneral;

import java.util.stream.Stream;

public interface IGenericService<T> {
    public void add(T item);
    public void remove(T item);
    public T get(long id);
    public Stream<T> stream();
}
