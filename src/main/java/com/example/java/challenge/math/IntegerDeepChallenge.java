package com.example.java.challenge.math;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * The depth of an integer n is defined to be how many multiples of n it is necessary to compute before all 10 digits
 * have appeared at least once in some multiple.
 * Write a function named computeDepth which computes the depth of its integer argument.
 * Only positive numbers greater than zero will be passed as an input.
 * For example:
 * Case 1: 1 -> 10 (hence it required 10 multiples of 1 to get all the digits, the depth of 1 is 10)
 * Case 2: 42 -> 9 (hence it required 9 multiples of 42 to get all the digits, the depth of 42 is 9)
 */
@Slf4j
public class IntegerDeepChallenge {

    /**
     * Computes depth of given number
     *
     * @param n int number grater than zero
     * @return int depth result
     */
    public static int computeDepth(final int n) {
        Set<String> digits = new HashSet<>();
        int depth = 0;

        while (digits.size() < 10) {
            String result = String.valueOf(n * ++depth);
            Collections.addAll(digits, result.split(""));
        }

        return depth;
    }
}
