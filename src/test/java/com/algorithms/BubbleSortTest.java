package com.algorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BubbleSortTest {

    @Test
    void sort() {
        Integer[] array = Utils.generate(Integer.class, 10, -2, 3, 0.5f);
        Integer[] result = BubbleSort.sort(array);
        boolean isSorted = Utils.isSorted(result);
        Assertions.assertTrue(isSorted);
    }
}
