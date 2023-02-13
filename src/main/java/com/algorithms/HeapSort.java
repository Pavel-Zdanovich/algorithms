package com.algorithms;

import static com.algorithms.Utils.swap;

public class HeapSort {

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

        buildMaxHeap(array);
        for (int i = array.length - 1; i >= 0; i--) {
            swap(array, 0, i);
            heapify(array, 0, i);
        }
        return array;
    }

    private static <T extends Comparable<T>> void buildMaxHeap(T[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            heapify(array, i, array.length);
        }
    }

    private static <T extends Comparable<T>> void heapify(T[] array, int root, int end) {
        int largest = root;
        int leftChild = 2 * root + 1;
        int rightChild = 2 * root + 2;

        if (leftChild < end && array[leftChild].compareTo(array[largest]) > 0) {
            largest = leftChild;
        }

        if (rightChild < end && array[rightChild].compareTo(array[largest]) > 0) {
            largest = rightChild;
        }

        if (largest != root) {
            swap(array, root, largest);
            heapify(array, largest, end);
        }
    }
}
