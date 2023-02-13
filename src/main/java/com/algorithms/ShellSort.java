package com.algorithms;

public class ShellSort {

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

        for (int interval = array.length / 2; interval > 0; interval = interval / 2) {
            for (int i = interval; i < array.length; i += 1) {
                T temp = array[i];
                int j;
                for (j = i; j >= interval && array[j - interval].compareTo(temp) > 0; j = j - interval) {
                    array[j] = array[j - interval];
                }
                array[j] = temp;
            }
        }
        return array;
    }
}
