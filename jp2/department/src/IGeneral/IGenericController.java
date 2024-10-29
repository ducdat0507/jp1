package IGeneral;

import java.util.Set;

public interface IGenericController<T> {
    public void add(T item);
    public void remove(T item);
    public Set<T> searchByName(String query);
}
