package com.algorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HeapSortTest {

    @Test
    void sort() {
        Integer[] array = Utils.generate(Integer.class, 1000, Integer.MIN_VALUE, Integer.MAX_VALUE, 1);
        Integer[] result = HeapSort.sort(array);
        boolean isSorted = Utils.isSorted(result);
        Assertions.assertTrue(isSorted);
    }
}
