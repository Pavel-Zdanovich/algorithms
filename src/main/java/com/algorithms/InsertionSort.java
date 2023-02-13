package com.algorithms;

import static com.algorithms.Utils.swap;

public class InsertionSort {

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

        for (int i = 0; i < array.length; i++) {
            for (int j = i; j > 0 && array[j].compareTo(array[j - 1]) < 0; j--) {
                swap(array, j, j - 1);
            }
        }
        return array;
    }

    public static <T extends Comparable<T>> T[] sentinelSort(T[] array) {
        int minElemIndex = 0;
        if (array.length < 1) {
            return array;
        }

        // put the smallest element to the 0 position as a sentinel, which will allow us to avoid
        // redundant comparisons like `j > 0` further
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(array[minElemIndex]) < 0) {
                minElemIndex = i;
            }
        }
        swap(array, 0, minElemIndex);

        for (int i = 2; i < array.length; i++) {
            int j = i;
            T currentValue = array[i];
            while (currentValue.compareTo(array[j - 1]) < 0) {
                array[j] = array[j - 1];
                j--;
            }

            array[j] = currentValue;
        }

        return array;
    }
}
