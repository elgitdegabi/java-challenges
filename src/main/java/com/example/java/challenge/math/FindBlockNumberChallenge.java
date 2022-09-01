package com.example.java.challenge.math;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * Your task is to construct a building which will be a pile of n cubes.
 * The cube at the bottom will have a volume of n^3, the cube above will have volume of (n-1)^3 and so on until the top which will have a volume of 1^3.
 * You are given the total volume m of the building.
 * The parameter of the function findNb (find_nb, find-nb, findNb, ...) will be an integer m and you have to
 * return the integer n such as n^3 + (n-1)^3 + ... + 1^3 = m if such a n exists or -1 if there is no such n.
 * For example:
 * Case 1: findNb(1071225) -> 45
 * Case 2: findNb(91716553919377) -> -1
 */
@Slf4j
public class FindBlockNumberChallenge {

    /**
     * Finds number of block from given long
     * IMPORTANT: use BigDecimal just to allow big values as result of Math.pow operation
     *
     * @param m long
     * @return number of blocks
     */
    public static long findNumberOfBlocks(final long m) {
        BigDecimal value = BigDecimal.ZERO;
        BigDecimal original = BigDecimal.valueOf(m);
        long index = 0;

        while (value.compareTo(original) < 0) {
            value = value.add(BigDecimal.valueOf(Math.pow(++index, 3)));
        }

        return (m % value.doubleValue() == 0 ? index : -1);
    }
}
