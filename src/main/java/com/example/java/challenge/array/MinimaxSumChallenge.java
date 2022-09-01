package com.example.java.challenge.array;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.List;

/**
 * Given five positive integers, find the minimum and maximum values that can be calculated by summing exactly four of
 * the five integers. Then print the respective minimum and maximum values as a single line of two space-separated long integers.
 * <p>
 * Example
 * Case 1: 1 2 3 4 5 -> prints 10 14
 * Case 2: 7 69 2 221 8974 -> prints 299 9271
 * Case 3: 256741038 623958417 467905213 714532089 938071625 -> prints 2063136757 2744467344
 */
@Slf4j
public class MinimaxSumChallenge {

    /**
     * Calculates minimax sum based on given list
     *
     * @param arr {@link List<Integer>}
     * @return {@link String} minimax value
     */
    public static String calculate(final List<Integer> arr) {
        BigInteger max = BigInteger.valueOf(arr.stream().sorted().skip(1).mapToLong(i -> i.intValue()).sum());
        BigInteger min = BigInteger.valueOf(arr.stream().sorted().limit(arr.size() - 1).mapToLong(i -> i.intValue()).sum());

        String miniMax = min + " " + max;
        System.out.println();

        return miniMax;
    }
}
