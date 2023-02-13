package com.algorithms;

/**
 * https://en.wikipedia.org/wiki/Counting_sort
 * Useful for sorting duplicate elements that belong to a range of values (integers, because keys as index in array)
 * less than the number of elements, i.e. the difference between the maximum
 * and minimum elements is less than or equal to the number of elements.
 * It is not a comparison sort.
 */
public class CountingSort {

    public static Integer[] sort(Integer[] array) {
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

        int min = array[0];
        int max = array[0];
        for (int value : array) {
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
        }

        int range = Math.addExact(Math.subtractExact(max, min), 1);

        int[] occurrences = new int[range];

        //number of occurrences of each value by index in normalized range
        for (int value : array) {
            occurrences[Math.subtractExact(value, min)]++; //normalization - min > 0 ? element - min : element + |min| => element - min
        }

        return
                simpleSort(array, occurrences, min);
//                squareRootSort(array, occurrences, min);
    }

    private static Integer[] simpleSort(Integer[] array, int[] occurrences, int min) {
        for (int i = 0, valueFromRange = 0; valueFromRange < occurrences.length; valueFromRange++) {
            int occurrencesOfValue = occurrences[valueFromRange];
            for (int j = 0; j < occurrencesOfValue; j++) {
                array[i++] = Math.addExact(valueFromRange, min); //denormalization
            }
        }

        return array;
    }

    private static Integer[] squareRootSort(Integer[] array, int[] occurrences, int min) {
        for (int i = 1; i < occurrences.length; i++) {
            occurrences[i] = Math.addExact(occurrences[i], occurrences[i - 1]);
        }

        Integer[] output = new Integer[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            int value = array[i];
            int normalizedValue = Math.subtractExact(value, min);
            int occurrencesOfValue = occurrences[normalizedValue];
            output[occurrencesOfValue - 1] = value;
            occurrencesOfValue--;
            occurrences[normalizedValue] = occurrencesOfValue;
        }

        return output;
    }
}
