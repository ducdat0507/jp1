package igeneric;

import java.util.Optional;

public interface IGenericService<T, TId> {
    public void add(T item);
    public Optional<T> get(TId id);
    public void delete(T item);
}
