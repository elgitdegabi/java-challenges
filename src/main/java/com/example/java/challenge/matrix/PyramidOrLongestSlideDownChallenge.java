package com.example.java.challenge.matrix;

import lombok.extern.slf4j.Slf4j;

/**
 * Imagine that you have a pyramid built of numbers. Let's say that the 'slide down' is the maximum sum of consecutive
 * numbers from the top to the bottom of the pyramid. As you can see, the longest 'slide down' is 3 + 7 + 4 + 9 = 23
 * Write a function that takes a pyramid representation as argument and returns its' largest 'slide down'.
 * <p>
 * IMPORTANT: AKA Maximum path sum (https://projecteuler.net/problem=18 or https://projecteuler.net/problem=67)
 * For example:
 * Case 1: {{3}, {7, 4}, {2, 4, 6}, {8, 5, 9, 3}} -> 3 + 7 + 4 + 9  -> 23
 */
@Slf4j
public class PyramidOrLongestSlideDownChallenge {

    /**
     * Executes sum of adjacent values from bottom to top of given pyramid
     *
     * @param pyramid int[][] pyramid matrix integer numbers
     * @return int sum of maximum adjacent values or zero
     */
    public static int longestSlideDown(final int[][] pyramid) {
        if (pyramid.length > 0) {
            int[][] maxAuxPyramid = pyramid;

            for (int i = pyramid.length - 2; i >= 0; i--) { // another approach: execute sum from bottom to top
                for (int j = 0; j < pyramid[i].length; j++) {
                    int current = pyramid[i][j]; // current position to sum
                    int bottomLeft = pyramid[i + 1][j]; // bottom adjacent left to sum
                    int bottomRight = pyramid[i + 1][j + 1]; // bottom adjacent right to sum

                    maxAuxPyramid[i][j] = current + Math.max(bottomLeft, bottomRight); // store sum result in aux pyramid
                }
            }

            return maxAuxPyramid[0][0];
        }

        return 0;
    }
}
