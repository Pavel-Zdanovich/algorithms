package com.algorithms;

import java.util.HashMap;
import java.util.Map;

public class RadixSort {

    public static final int RADIX = 10; //base of the number system

    public static Integer[] sort(Integer[] array) {
        if (array == null) {
            throw new RuntimeException("Array is null");
        }
        validate(array);
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

        int max = array[0];
        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }

        for (int exp = 1; max / exp > 0; exp = exp * RADIX) {
            array = sortByNumberDigit(array, exp);
        }

        return array;
    }

    private static void validate(Integer[] array) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0) {
                map.put(i, array[i]);
            }
        }
        if (!map.isEmpty()) {
            throw new RuntimeException("Invalid elements: %s".formatted(map));
        }
    }

    private static Integer[] sortByNumberDigit(Integer[] array, int exp) {
        int[] occurrences = new int[RADIX];
        for (int value : array) {
            occurrences[(value / exp) % RADIX]++;
        }

        for (int i = 1; i < 10; i++) {
            occurrences[i] = occurrences[i] + occurrences[i - 1];
        }

        Integer[] output = new Integer[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            int value = array[i];
            int index = (value / exp) % RADIX;
            int occurrencesOfValue = occurrences[index];
            output[occurrencesOfValue - 1] = value;
            occurrencesOfValue--;
            occurrences[index] = occurrencesOfValue;
        }

        return output;
    }
}
