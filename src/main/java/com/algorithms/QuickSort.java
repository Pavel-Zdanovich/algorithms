package com.algorithms;

import static com.algorithms.Utils.swap;

public class QuickSort {

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

//        return twoWaySort(array, 0, array.length - 1);
        return threeWaySort(array, 0, array.length - 1);
    }

    private static <T extends Comparable<T>> T[] twoWaySort(T[] array, int left, int right) {
        if (left < right) {
            int[] pivot =
                    partitionLomuto(array, left, right);
//                    partitionHoare(array, left, right);
//                    partitionHoareRight(array, left, right);
//                    fatPartition(array, left, right);
            twoWaySort(array, left, pivot[0] - 1);
            twoWaySort(array, pivot[1] + 1, right);
        }
        return array;
    }

    private static <T extends Comparable<T>> int[] partitionLomuto(T[] array, int left, int right) {
        int borderLeft = left - 1;
        int index = left - 1;
        T pivot = array[right];
        while (true) {
            do {
                index++;
            } while (array[index].compareTo(pivot) >= 0 && index < right);
            if (index >= right) {
                break;
            }
            borderLeft++;
            swap(array, borderLeft, index);
        }
        borderLeft++;
        swap(array, borderLeft, right);
        return new int[]{borderLeft, borderLeft};
    }

    private static <T extends Comparable<T>> int[] partitionHoare(T[] array, int left, int right) {
        int leftBorder = left - 1;
        int rightBorder = right + 1;
        T pivot = array[(left + right) / 2];
        while (true) {
            do {
                leftBorder++;
            } while (array[leftBorder].compareTo(pivot) < 0); //until found element >= pivot
            do {
                rightBorder--;
            } while (array[rightBorder].compareTo(pivot) > 0); //until found element <= pivot
            if (leftBorder >= rightBorder) {

                return new int[]{rightBorder + 1, rightBorder};

            }
            swap(array, leftBorder, rightBorder);
        }
    }

    private static <T extends Comparable<T>> int[] partitionHoareRight(T[] array, int left, int right) {
        int leftBorder = left - 1;
        int rightBorder = right;
        T pivot = array[right];
        while (true) {
            do {
                leftBorder++;
            } while (array[leftBorder].compareTo(pivot) < 0); //until found element >= pivot
            do {
                rightBorder--;
            } while (array[rightBorder].compareTo(pivot) > 0 && rightBorder > left); //until found element <= pivot

            if (leftBorder >= rightBorder) {
                break;
            }
            swap(array, leftBorder, rightBorder);
        }
        swap(array, leftBorder, right);
        return new int[]{rightBorder + 1, rightBorder + 1};
    }

    private static <T extends Comparable<T>> int[] fatPartition(T[] array, int left, int right) {
        int leftBorder = left;
        int rightBorder = right;
        T pivot = array[right];
        //special case of Dutch national flag problem
        int current = leftBorder;
        while (current <= rightBorder) {
            if (array[current].compareTo(pivot) < 0) {
                swap(array, current, leftBorder);
                leftBorder++;
                current++;
            } else if (array[current].compareTo(pivot) > 0) {
                swap(array, rightBorder, current);
                rightBorder--;
            } else {
                current++;
            }
        }
        return new int[]{leftBorder, rightBorder};
    }

    private static <T extends Comparable<T>> T[] threeWaySort(T[] array, int left, int right) {
        if (left < right) {
            int[] pivots = partitionDijkstra(array, left, right);
            threeWaySort(array, left, pivots[0] - 1);
            threeWaySort(array, pivots[0] + 1, pivots[1] - 1);
            threeWaySort(array, pivots[1] + 1, right);
        }
        return array;
    }

    private static <T extends Comparable<T>> int[] partitionDijkstra(T[] array, int left, int right) {
        if (array[left].compareTo(array[right]) > 0) {
            swap(array, left, right);
        }

        T pivotLeft = array[left];
        T pivotRight = array[right];

        int leftBorder = left + 1;
        int rightBorder = right - 1;

        //general case of Dutch national flag problem
        int current = leftBorder;
        while (current <= rightBorder) {
            if (array[current].compareTo(pivotLeft) < 0) {
                swap(array, current, leftBorder);
                leftBorder++;
                current++;
            } else if (array[current].compareTo(pivotRight) > 0) {
                swap(array, rightBorder, current);
                rightBorder--;
            } else {
                current++;
            }
        }

        leftBorder--;
        rightBorder++;
        swap(array, left, leftBorder);
        swap(array, right, rightBorder);

        return new int[]{leftBorder, rightBorder};
    }
}
