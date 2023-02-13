package com.algorithms;

import java.util.*;
import java.util.stream.Stream;

public class Utils {

    public static final Integer MAX_ARRAY_SIZE = 10_000_000;

    public static <T extends Number> T[] generate(Class<T> tClass, int size, T min, T max, float uniqueness) {
        if (tClass == null) {
            throw new RuntimeException("Class is null");
        }
        if (size < 1 || size > MAX_ARRAY_SIZE) {
            throw new RuntimeException("Size is invalid: %d".formatted(size));
        }
        if (min.doubleValue() > max.doubleValue()) {
            throw new RuntimeException("Min and max are invalid: %f > %f".formatted(min.doubleValue(), max.doubleValue()));
        }
        if (uniqueness <= 0 || uniqueness > 1) {
            throw new RuntimeException("Uniqueness is invalid: %f".formatted(uniqueness));
        }
        if (uniqueness == 1) {
            int diff = (int) Math.abs(max.doubleValue() - min.doubleValue());
            if (diff == size) {
                T[] result = iterate(tClass, size, min, max);
                shuffle(result);
                return result;
            }
            if (diff < size) {
                throw new RuntimeException("Range of values [%f, %f) doesn't allow to generate an array of size %d"
                        .formatted(min.doubleValue(), max.doubleValue(), size));
            }
        }

        float uniquePart = 1 / uniqueness;
        int sizeOfUnique = (int) (size / uniquePart);

        Map<T, Integer> map = generate(tClass, sizeOfUnique, min, max);

        T[] unique;
        T[] result;
        switch (tClass.getSimpleName()) {
            case "Integer" -> {
                unique = (T[]) map.keySet().toArray(Integer[]::new);
                if (uniqueness == 1) {
                    return unique;
                }
                result = (T[]) new Integer[size];
            }
            case "Long" -> {
                unique = (T[]) map.keySet().toArray(Long[]::new);
                if (uniqueness == 1) {
                    return unique;
                }
                result = (T[]) new Long[size];
            }
            case "Float" -> {
                unique = (T[]) map.keySet().toArray(Float[]::new);
                if (uniqueness == 1) {
                    return unique;
                }
                result = (T[]) new Float[size];
            }
            case "Double" -> {
                unique = (T[]) map.keySet().toArray(Double[]::new);
                if (uniqueness == 1) {
                    return unique;
                }
                result = (T[]) new Double[size];
            }
            default -> throw new RuntimeException("Unknown class: %s".formatted(tClass.getSimpleName()));
        }
        reproduce((int) uniquePart, unique, result);
        return result;
    }

    private static <T extends Number> T[] iterate(Class<T> tClass, int size, T min, T max) {
        switch (tClass.getSimpleName()) {
            case "Integer" -> {
                return (T[]) Stream
                        .iterate(min.intValue(), n -> n + 1)
                        .limit(size)
                        .toArray(Integer[]::new);
            }
            case "Long" -> {
                return (T[]) Stream
                        .iterate(min.longValue(), n -> n + 1)
                        .limit(size)
                        .toArray(Long[]::new);
            }
            case "Float" -> {
                Map<T, Integer> map = generate(tClass, size, min, max);
                return (T[]) map.keySet().toArray(Float[]::new);
            }
            case "Double" -> {
                Map<T, Integer> map = generate(tClass, size, min, max);
                return (T[]) map.keySet().toArray(Double[]::new);
            }
            default -> throw new RuntimeException("Unknown class: %s".formatted(tClass.getSimpleName()));
        }
    }

    private static <T> void shuffle(T[] array) {
        Random random = new Random();
        for (int i = array.length; i > 1; i--) {
            swap(array, i - 1, random.nextInt(i));
        }
    }

    private static <T extends Number> Map<T, Integer> generate(Class<T> tClass, int size, T min, T max) {
        Random random = new Random();
        Map<T, Integer> unique = new LinkedHashMap<>(size);
        while (unique.size() < size) {
            switch (tClass.getSimpleName()) {
                case "Integer" -> {
                    Integer integer = random.nextInt(min.intValue(), max.intValue());
                    T t = (T) integer;
                    Integer frequency = unique.getOrDefault(t, 0);
                    frequency++;
                    unique.put(t, frequency);
                }
                case "Long" -> {
                    Long l = random.nextLong(min.longValue(), max.longValue());
                    T t = (T) l;
                    Integer frequency = unique.getOrDefault(t, 0);
                    frequency++;
                    unique.put(t, frequency);
                }
                case "Float" -> {
                    Float f = random.nextFloat(min.floatValue(), max.floatValue());
                    T t = (T) f;
                    Integer frequency = unique.getOrDefault(t, 0);
                    frequency++;
                    unique.put(t, frequency);
                }
                case "Double" -> {
                    Double d = random.nextDouble(min.doubleValue(), max.doubleValue());
                    T t = (T) d;
                    Integer frequency = unique.getOrDefault(t, 0);
                    frequency++;
                    unique.put(t, frequency);
                }
                default -> throw new RuntimeException("Unknown class: %s".formatted(tClass.getSimpleName()));
            }
        }
        return unique;
    }

    private static <T extends Number> void reproduce(int uniquePart, T[] unique, T[] all) {
        int i;
        for (i = 0; i < uniquePart; i++) {
            System.arraycopy(unique, 0, all, i * unique.length, unique.length);
        }
        System.arraycopy(unique, 0, all, i * unique.length, all.length - (i * unique.length));
    }

    public static <T extends Comparable<T>> boolean isSorted(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].compareTo(array[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean hasDuplicates(final Integer[] integers) {
        BitSet negative = new BitSet(Integer.MAX_VALUE);
        BitSet positive = new BitSet(Integer.MAX_VALUE);
        BitSet bitSet;
        for (Integer integer : integers) {
            if (integer < 0) {
                integer = Integer.MAX_VALUE + integer;
                bitSet = negative;
            } else {
                bitSet = positive;
            }
            if (!bitSet.get(integer)) {
                bitSet.set(integer);
            } else {
                return true;
            }
        }
        return false;
    }

//    public static boolean hasDuplicates(final Long[] longs) {
//        List<BitSet> negative = new ArrayList<>();
//        List<BitSet> positive = new ArrayList<>();
//        BitSet bitSet;
//        for (Long l : longs) {
//            if (l < 0) {
//                l = Long.MAX_VALUE + l;
//            } else {
//            }
//            int integer = l;
//            if (!bitSet.get(integer)) {
//                bitSet.set(integer);
//            } else {
//                return true;
//            }
//        }
//        return false;
//    }

    public static <T> void swap(T[] array, int left, int right) {
        T swap = array[left];
        array[left] = array[right];
        array[right] = swap;
    }
}
