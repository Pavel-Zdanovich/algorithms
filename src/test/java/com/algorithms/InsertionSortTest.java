package com.algorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InsertionSortTest {

    @Test
    void sort() {
        Integer[] array = Utils.generate(Integer.class, 1000, Integer.MIN_VALUE, Integer.MAX_VALUE, 1);
        Integer[] result = InsertionSort.sort(array);
        boolean isSorted = Utils.isSorted(result);
        Assertions.assertTrue(isSorted);
    }
}
