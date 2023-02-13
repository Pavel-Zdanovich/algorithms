package com.algorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CountingSortTest {

    @Test
    void sort() {
        Integer[] array = Utils.generate(Integer.class, 1000, 0, 1000, 1);
        Integer[] result = CountingSort.sort(array);
        boolean isSorted = Utils.isSorted(result);
        Assertions.assertTrue(isSorted);
    }
}
