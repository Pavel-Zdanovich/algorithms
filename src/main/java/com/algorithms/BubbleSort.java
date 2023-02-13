package com.algorithms;

import static com.algorithms.Utils.swap;

public class BubbleSort {

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
        return cyclic(array);
//        return recursive(array, array.length);
    }

    private static <T extends Comparable<T>> T[] cyclic(T[] array) {
        for (int i = 1; i < array.length; i++) {
            boolean swapped = false;
            for (int j = 0; j < array.length - i; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    swap(array, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        return array;
    }

    private static <T extends Comparable<T>> T[] recursive(T[] array, int length) {
        boolean swapped = false;
        for (int i = 0; i < length - 1; i++) {
            if (array[i].compareTo(array[i + 1]) > 0) {
                swap(array, i, i + 1);
                swapped = true;
            }
        }
        if (swapped) {
            recursive(array, length - 1);
        }
        return array;
    }
}
