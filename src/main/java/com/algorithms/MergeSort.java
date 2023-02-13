package com.algorithms;

public class MergeSort {

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

        return split(array, 0, array.length - 1, (T[]) new Comparable[array.length]);
    }

    private static <T extends Comparable<T>> T[] split(T[] array, int left, int right, T[] result) {
        if (left < right) {
            int middle = (left + right) / 2;
            split(array, left, middle, result);
            split(array, middle + 1, right, result);
            merge(array, left, middle, right, result);
        }
        return array;
    }

    private static <T extends Comparable<T>> void merge(T[] array, int left, int middle, int right, T[] result) {
        int i = left;
        int j = middle + 1;

        System.arraycopy(array, left, result, left, right - left + 1);

        for (int k = left; k <= right; k++) {
            if (j > right) {
                array[k] = result[i];
                i++;
            } else if (i > middle) {
                array[k] = result[j];
                j++;
            } else if (result[j].compareTo(result[i]) < 0) {
                array[k] = result[j];
                j++;
            } else {
                array[k] = result[i];
                i++;
            }
        }
    }
}
