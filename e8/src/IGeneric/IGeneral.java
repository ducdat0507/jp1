package IGeneric;

import java.util.List;
import java.util.Optional;

public interface IGeneral<T> {
    public Optional<T> getById(int id);
    public Optional<T> getByCode(String code);
    public List<T> getByName(String keyword);
}
