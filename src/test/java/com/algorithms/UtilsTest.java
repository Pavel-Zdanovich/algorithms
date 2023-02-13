package com.algorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UtilsTest {

    @Test
    void test1() {
        int size = 10;
        int min = -5;
        int max = 5;
        float uniqueness = 1;
        Integer[] integers = Utils.generate(Integer.class, size, min, max, uniqueness);
        IntSummaryStatistics statistics = Stream.of(integers).collect(Collectors.summarizingInt(n -> n));
        Assertions.assertEquals(size, statistics.getCount());
        Assertions.assertTrue(min <= statistics.getMin());
        Assertions.assertTrue(max >= statistics.getMax());
        Assertions.assertFalse(Utils.hasDuplicates(integers));
    }


    @Test
    void test2() {
        int size = 10;
        int min = -5;
        int max = 5;
        float uniqueness = 0.5F;
        Integer[] integers = Utils.generate(Integer.class, size, min, max, uniqueness);
        IntSummaryStatistics statistics = Stream.of(integers).collect(Collectors.summarizingInt(n -> n));
        Assertions.assertEquals(size, statistics.getCount());
        Assertions.assertTrue(min <= statistics.getMin());
        Assertions.assertTrue(max >= statistics.getMax());
        Assertions.assertTrue(Utils.hasDuplicates(integers));
    }

    @Test
    void test3() {
        Assertions.assertThrows(
                RuntimeException.class,
                () -> Utils.generate(null, 1, 1, 1, 1),
                "Class is null"
        );
    }

    @Test
    void test4() {
        Assertions.assertThrows(
                RuntimeException.class,
                () -> Utils.generate(Integer.class, 0, 1, 1, 1),
                "Size is invalid: 0"
        );
        Assertions.assertThrows(
                RuntimeException.class,
                () -> Utils.generate(Integer.class, Utils.MAX_ARRAY_SIZE, 1, 1, 1),
                "Size is invalid: %d".formatted(Utils.MAX_ARRAY_SIZE)
        );
    }

    @Test
    void test5() {
        Assertions.assertThrows(
                RuntimeException.class,
                () -> Utils.generate(Integer.class, 1, 1, 0, 1),
                "Min and max are invalid: 0 > 1"
        );
    }

    @Test
    void test6() {
        Assertions.assertThrows(
                RuntimeException.class,
                () -> Utils.generate(Integer.class, 1, 1, 1, 0),
                "Uniqueness is invalid: 0"
        );
        Assertions.assertThrows(
                RuntimeException.class,
                () -> Utils.generate(Integer.class, 1, 1, 1, 2),
                "Uniqueness is invalid: 2"
        );
    }


    @Test
    void test7() {
        Assertions.assertThrows(
                RuntimeException.class,
                () -> Utils.generate(Integer.class, 10, 1, 10, 1),
                "Range of values [%f, %f) doesn't allow to generate an array of size %d".formatted(1.0, 10.0, 10)
        );
    }


    @Test
    void test8() {
        Assertions.assertThrows(
                RuntimeException.class,
                () -> Utils.generate(BigDecimal.class, 1, BigDecimal.ONE, BigDecimal.ONE, 1),
                "Unknown class: BigDecimal"
        );
    }

}
