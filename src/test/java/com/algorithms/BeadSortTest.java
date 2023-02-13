package com.algorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BeadSortTest {

    @Test
    void sort() {
        Integer[] array = Utils.generate(Integer.class, 1000, 1, 1001, 1);
        Integer[] result = BeadSort.sort(array);
        boolean isSorted = Utils.isSorted(result);
        Assertions.assertTrue(isSorted);
    }
}
