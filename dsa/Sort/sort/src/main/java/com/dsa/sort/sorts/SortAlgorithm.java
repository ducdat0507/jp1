package com.dsa.sort.sorts;

import java.util.Comparator;
import java.util.List;

public interface SortAlgorithm<T> {
    public List<T> sort(List<T> list, Comparator<T> comparator);
}
