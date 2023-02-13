package com.algorithms;

import static com.algorithms.Utils.swap;

public class SelectionSort {

    public static <T extends Comparable<T>> T[] sort(T[] array) {
        if (array == null) {
            throw new RuntimeException("Array is null");
        }
        switch (array.length) {
            case 0 -> throw new RuntimeException("Array is empty");
            case 1 -> {
                return array;
            }
            case 2 -> {
                if (array[0].compareTo(array[1]) > 0) {
                    Utils.swap(array, 0, 1);
                }
                return array;
            }
        }

        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[minIndex].compareTo(array[j]) > 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(array, i, minIndex);
            }
        }
        return array;
    }
}
