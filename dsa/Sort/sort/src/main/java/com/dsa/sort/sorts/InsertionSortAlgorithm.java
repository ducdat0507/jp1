package com.dsa.sort.sorts;

import java.util.Comparator;
import java.util.List;

public class InsertionSortAlgorithm<T> implements SortAlgorithm<T> {

    @Override
    public List<T> sort(List<T> list, Comparator<T> comparator) {
        int len = list.size();

        for (int i = 1; i < len; i++) {
            T value = list.get(i);
            int j = i - 1;
            while (j >= 0 && comparator.compare(list.get(j), value) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, value);
        }

        return list;
    }
    
}
