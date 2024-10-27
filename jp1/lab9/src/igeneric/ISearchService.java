package igeneric;

import java.util.List;

public interface ISearchService<T> {
    public List<T> search(String query);
}
