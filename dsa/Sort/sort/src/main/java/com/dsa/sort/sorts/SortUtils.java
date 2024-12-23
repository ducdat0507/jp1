package com.dsa.sort.sorts;

import java.util.List;

class SortUtils {
    private SortUtils() { /* */ }

    public static <T> void swap(T[] list, int index1, int index2) {
        T temp = list[index1];
        list[index1] =  list[index2];
        list[index2] =  temp;
    }
}
