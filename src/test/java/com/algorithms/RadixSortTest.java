package com.algorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RadixSortTest {

    @Test
    void repeatingNonNegativeIntegers() {
        Integer[] array = Utils.generate(Integer.class, 10, 0, 5, 0.5f);
        Integer[] result = RadixSort.sort(array);
        boolean isSorted = Utils.isSorted(result);
        Assertions.assertTrue(isSorted);
    }

    @Test
    void negativeIntegers() {
        Assertions.assertThrows(
                RuntimeException.class,
                () -> RadixSort.sort(Utils.generate(Integer.class, 10, -5, 5, 1)),
                "Invalid elements"
        );
    }
}
