package com.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BucketSort {

    public static Integer[] sort(Integer[] array) { //TODO can't be generalized due to type operations
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

        int numberOfBuckets = Math.addExact(Math.subtractExact(max, min), 1);

        List<List<Integer>> buckets = new ArrayList<>(numberOfBuckets);

        for (int i = 0; i < numberOfBuckets; i++) {
            buckets.add(new ArrayList<>());
        }

        for (int value : array) {
            int hash = Math.subtractExact(value, min) / numberOfBuckets;
            buckets.get(hash).add(value);
        }

        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket); //TODO
        }

        int index = 0;
        for (List<Integer> bucket : buckets) {
            for (int value : bucket) {
                array[index++] = value;
            }
        }

        return array;
    }
}
