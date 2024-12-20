package com.dsa.sort.sorts;

import java.util.Comparator;
import java.util.List;

public class SelectionSortAlgorithm<T> implements SortAlgorithm<T> {

    @Override
    public List<T> sort(List<T> list, Comparator<T> comparator) {
        int len = list.size();

        for (int i = 0; i < len - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (comparator.compare(list.get(j), list.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) SortUtils.swap(list, i, minIndex);
        }

        return list;
    }
    
}
