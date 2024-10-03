package IGeneric;

import java.util.List;
import java.util.Optional;

public interface IManager<T> {
    public Optional<T> getById(int id);
    public Optional<T> getByCode(String code);
    public List<T> queryByName(String query);
}
