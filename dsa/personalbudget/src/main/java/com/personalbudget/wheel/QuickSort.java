package com.personalbudget.wheel;

import java.util.Comparator;

public class QuickSort {
    public static <T> void sort(T[] array, Comparator<T> comparator) {
        internalSort(array, comparator, 0, array.length - 1);
    }

    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static <T> int partition(T[] array, Comparator<T> comparator, int low, int high) {
        T pivot = array[high];

        int i = low - 1;

        // Traverse arr[low..high] and move all smaller
        // elements to the left side. Elements from low to 
        // i are smaller after every iteration
        for (int j = low; j <= high - 1; j++) {
            if (comparator.compare(array[j], pivot) < 0) {
                i++;
                swap(array, i, j);
            }
        }
        
        // Move pivot after smaller elements and
        // return its position
        swap(array, i + 1, high);  
        return i + 1;
    }
        
    private static <T> void internalSort(T[] array, Comparator<T> comparator, int low, int high) {
        if (low < high) {
            int sep = partition(array, comparator, low, high);
            internalSort(array, comparator, low, sep - 1);
            internalSort(array, comparator, sep + 1, high);
        }
    }
}
