package com.example.java.challenge.array;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Given an array of integers, calculate the median: middle element after sorting.
 * For example:
 * Case 1: 5, 3, 1, 2, 4 ->  3
 * Case 2: 0, 1, 2, 4, 6, 5, 3 ->  3
 */
@Slf4j
public class FindMedianChallenge {
    public static void main(String[] args) {
        log.info("find median start");
        log.info("median is: {}", FindMedian.calculate(List.of(0, 1, 2, 4, 6, 5, 3)));
        log.info("find median end");
    }

    /**
     * Find Median class
     */
    private static class FindMedian {

        /**
         * Calculates median after sorting given list
         * @param arr {@link List<Integer>}
         * @return median (middle element value)
         */
        public static int calculate(final List<Integer> arr) {
            List<Integer> sortedArr = arr.stream().sorted().collect(Collectors.toList());
            return sortedArr.get(arr.size() / 2);
        }
    }
}
