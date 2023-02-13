package com.algorithms;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(Test.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(options).run();
    }

    @State(Scope.Benchmark)
    public static class Array {

        private Integer[] integers;

        @Setup
        public void setup() {
            integers = Utils.generate(Integer.class, 1000, Integer.MIN_VALUE, Integer.MAX_VALUE, 1);
        }

        public Integer[] get() {
            return Arrays.copyOf(integers, integers.length);
        }
    }

    @Benchmark
    public Object bubbleSort(Array array) {
        return BubbleSort.sort(array.get());
    }

    @Benchmark
    public Object bucketSort(Array array) {
        return BucketSort.sort(array.get());
    }

    @Benchmark
    public Object countingSort(Array array) {
        return CountingSort.sort(array.get());
    }

    @Benchmark
    public Object heapSort(Array array) {
        return HeapSort.sort(array.get());
    }

    @Benchmark
    public Object insertionSort(Array array) {
        return InsertionSort.sort(array.get());
    }

    @Benchmark
    public Object mergeSort(Array array) {
        return MergeSort.sort(array.get());
    }

    @Benchmark
    public Object quickSort(Array array) {
        return QuickSort.sort(array.get());
    }

    @Benchmark
    public Object selectionSort(Array array) {
        return SelectionSort.sort(array.get());
    }

    @Benchmark
    public Object shellSort(Array array) {
        return ShellSort.sort(array.get());
    }
}
