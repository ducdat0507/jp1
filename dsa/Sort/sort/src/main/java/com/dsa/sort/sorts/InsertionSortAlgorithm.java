package com.dsa.sort.sorts;

import java.util.Comparator;
import java.util.List;

public class InsertionSortAlgorithm<T> implements SortAlgorithm<T> {

    @Override
    public T[] sort(T[] list, Comparator<T> comparator) {
        int len = list.length;

        for (int i = 1; i < len; i++) {
            T value = list[i];
            int j = i - 1;
            while (j >= 0 && comparator.compare(list[j], value) > 0) {
                list[j + 1] = list[j];
                j--;
            }
            list[j + 1] = value;
        }

        return list;
    }
    
}
