package com.algorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BucketSortTest {

    @Test
    void sort() {
        Integer[] array = Utils.generate(Integer.class, 1000, 0, 1000, 1);
        Integer[] result = BucketSort.sort(array);
        boolean isSorted = Utils.isSorted(result);
        Assertions.assertTrue(isSorted);
    }
}
