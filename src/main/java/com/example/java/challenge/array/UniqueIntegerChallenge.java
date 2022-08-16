package com.example.java.challenge.array;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Given an array of integers, where all elements but one occur twice, find the unique element.
 * For example:
 * Case 1: 1, 2, 3, 4, 3, 2, 1 ->  4
 * Case 2: 0 0 1 2 1 ->  2
 */
@Slf4j
public class UniqueIntegerChallenge {
    public static void main(String[] args) {
        log.info("unique integer start");
        log.info("unique integer is: {}", UniqueInteger.find(List.of(1, 2, 3, 4, 3, 2, 1)));
        log.info("unique integer end");
    }

    /**
     * Unique integer class
     */
    private static class UniqueInteger {

        /**
         * Finds unique element from given list
         * @param arr {@link List<Integer>}
         * @return unique element
         */
        public static int find(final List<Integer> arr) {
            Map<Integer, List<Integer>> countDuplicatesMap = arr.stream().collect(Collectors.groupingBy(Function.identity()));
            return countDuplicatesMap.values().stream().filter(e -> e.size() == 1).findFirst().orElse(Collections.emptyList()).get(0);
        }
    }
}
