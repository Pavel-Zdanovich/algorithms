package com.algorithms;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class BeadSort {

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

    public static Integer[] sort(Integer[] array) {
        if (array == null) {
            throw new RuntimeException("Array is null");
        }
        switch (array.length) {
            case 0 -> throw new RuntimeException("Array is empty");
            case 1 -> {
                validate(array);
                return array;
            }
            case 2 -> {
                validate(array);
                if (array[0].compareTo(array[1]) > 0) {
                    Utils.swap(array, 0, 1);
                }
                return array;
            }
        }

        validate(array);

        //set up abacus
        int max = array[0];
        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }
        int numberOfSpokes = max;
        BitSet[] abacus = new BitSet[array.length]; //boolean[array.length][numberOfSpokes]
        for (int i = 0; i < array.length; i++) {
            abacus[i] = new BitSet(numberOfSpokes);
        }
        int[] knuckles = new int[numberOfSpokes];

        //drop the beads
        for (int numberOfKnuckles : array) {
            for (int i = 0; i < numberOfKnuckles; i++) { //stringing knuckles from first spoke
                int count = knuckles[i];
                abacus[count].set(i); //drop the bead
                count++;
                knuckles[i] = count;
            }
        }

        //count the beads
        for (int i = 0; i < array.length; i++) {
            int count = 0;
            for (int j = 0; j < numberOfSpokes; j++) {
                if (abacus[array.length - 1 - i].get(j)) {
                    count++;
                } else {
                    break;
                }
            }
            array[i] = count;
        }

        return array;
    }
}
