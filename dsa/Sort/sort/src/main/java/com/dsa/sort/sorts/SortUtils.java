package com.dsa.sort.sorts;

import java.util.List;

class SortUtils {
    public static <T> void swap(List<T> list, int index1, int index2) {
        T temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }
}
