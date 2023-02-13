package com.algorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BeadSortTest {

    @Test
    void repeatingNonNegativeIntegers() {
        Integer[] array = Utils.generate(Integer.class, 10, 1, 6, 0.5f);
        Integer[] result = BeadSort.sort(array);
        boolean isSorted = Utils.isSorted(result);
        Assertions.assertTrue(isSorted);
    }

    @Test
    void negativeIntegers() {
        Assertions.assertThrows(
                RuntimeException.class,
                () -> BeadSort.sort(Utils.generate(Integer.class, 10, -5, 5, 1)),
                "Invalid elements"
        );
    }
}
