package com.algorithms;

public class GraphSort {

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

        //preparation
        int[][] ints = new int[array.length][];
        for (int i = 0; i < array.length; i++) {
            ints[i] = new int[]{0, array[i]};
        }

        //sorting
        int a = 1;
        boolean sorted = true;
        do {
            for (int i = 0, j = a; j < array.length; i++, j++) {
                int a1 = array[i];
                int a2 = array[j];

                if (a1 > a2) {
                    ints[i][0]++;
                } else {
                    ints[j][0]++;
                }

                if (sorted) {
                    sorted = ints[i][0] == 0 || ints[i][0] == 1;
                }
            }

            a++;

            if (sorted) {
                break;
            } else {
                sorted = a < array.length;
            }
        } while (sorted);

        //output
        if (sorted) {
            if (ints[0][0] == 0) {
                return array;
            }
            if (ints[array.length - 1][0] == 0) {
                for (int i = 0, j = array.length - 1; i < array.length; i++, j++) {
                    array[i] = ints[j][1];
                }
                return array;
            }
        }

        for (int i = 0; i < array.length; i++) {
            array[ints[i][0]] = ints[i][1];
        }

        return array;
    }
}
