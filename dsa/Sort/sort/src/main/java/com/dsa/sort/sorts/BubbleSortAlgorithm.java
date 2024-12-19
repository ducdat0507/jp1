package com.dsa.sort.sorts;

import java.util.Comparator;
import java.util.List;

public class BubbleSortAlgorithm<T> implements SortAlgorithm<T> {

    @Override
    public List<T> sort(List<T> list, Comparator<T> comparator) {
        int len = list.size();

        boolean swapped = false;

        do {
            swapped = false;
            for (int i = 0; i < len - 1; i++) {
                if (comparator.compare(list.get(i), list.get(i + 1)) > 0) {
                    SortUtils.swap(list, i, i + 1);
                    swapped = true;
                }
            }
            len--;
        } while (swapped);

        return list;
    }
    
}