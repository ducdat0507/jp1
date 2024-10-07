package igeneric;

public interface IContainerService<T> {
    public void add(T item);
    public T get(int id);
}
