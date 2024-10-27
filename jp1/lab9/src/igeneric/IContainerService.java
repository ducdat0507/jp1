package igeneric;

import java.util.Comparator;
import java.util.List;

public interface IContainerService<T> {
    public void add(T item);
    public T get(int id);
    public List<T> list(int limit, int offset);
    public List<T> list(Comparator<? super T> sort, int limit, int offset);
}
