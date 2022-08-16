package com.example.java.challenge.array;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Given a list of integers, count and return the number of times each value appears as an array of integers.
 * For example:
 * Case 1: 1, 1, 1, 2, 3 ->  0, 3, 1, 1
 * Case 2: 63, 25, 73, 1, 98, 73, 56, 84, 86, 57, 16, 83, 8, 25, 81, 56, 9, 53, 98, 67, 99, 12, 83, 89, 80, 91, 39, 86,
 * 76, 85, 74, 39, 25, 90, 59, 10, 94, 32, 44, 3, 89, 30, 27, 79, 46, 96, 27, 32, 18, 21, 92, 69, 81, 40, 40, 34, 68,
 * 78, 24, 87, 42, 69, 23, 41, 78, 22, 6, 90, 99, 89, 50, 30, 20, 1, 43, 3, 70, 95, 33, 46, 44, 9, 69, 48, 33, 60, 65,
 * 16, 82, 67, 61, 32, 21, 79, 75, 75, 13, 87, 70, 33 ->
 * 0, 2, 0, 2, 0, 0, 1, 0, 1, 2, 1, 0, 1, 1, 0, 0, 2, 0, 1, 0, 1, 2, 1, 1, 1, 3, 0, 2, 0, 0, 2, 0, 3, 3, 1, 0, 0, 0, 0,
 * 2, 2, 1, 1, 1, 2, 0, 2, 0, 1, 0, 1, 0, 0, 1, 0, 0, 2, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 2, 1, 3, 2, 0, 0, 2, 1, 2, 1, 0,
 * 2, 2, 1, 2, 1, 2, 1, 1, 2, 2, 0, 3, 2, 1, 1, 0, 1, 1, 1, 0, 2, 2
 */
@Slf4j
public class CountingSortChallenge {
    private static final int COUNT_RANGE = 4; // IMPORTANT: fix number range, for case 1 should be 0..3; for case 2: 0..100

    public static void main(String[] args) {
        log.info("counting sort start");
        log.info("counting sort is: {}", CountingSort.calculate(List.of(1, 1, 1, 2, 3)));
        log.info("counting sort end");
    }

    /**
     * Counting Sort class
     */
    private static class CountingSort {

        /**
         * Calculates counting sorting based on given list
         * @param arr {@link List<Integer>}
         * @return {@link List<Integer>} counting sort
         */
        public static List<Integer> calculate(final List<Integer> arr) {
            Map<Integer, List<Integer>> countMap = arr.stream().collect(Collectors.groupingBy(Function.identity()));
            List<Integer> resultList = new ArrayList<>();
            IntStream.range(0, COUNT_RANGE).forEach(i -> resultList.add(countMap.getOrDefault(i, Collections.emptyList()).size()));

            return resultList;
        }
    }
}
