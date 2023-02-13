package com.algorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RadixSortTest {

    @Test
    void sort() {
        Integer[] array = Utils.generate(Integer.class, 1000, 1, Integer.MAX_VALUE, 1);
        Integer[] result = RadixSort.sort(array);
        boolean isSorted = Utils.isSorted(result);
        Assertions.assertTrue(isSorted);
    }
}
