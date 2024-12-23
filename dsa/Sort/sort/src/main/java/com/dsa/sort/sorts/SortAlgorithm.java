package com.dsa.sort.sorts;

import java.util.Comparator;
import java.util.List;

public interface SortAlgorithm<T> {
    public T[] sort(T[] list, Comparator<T> comparator);
}
