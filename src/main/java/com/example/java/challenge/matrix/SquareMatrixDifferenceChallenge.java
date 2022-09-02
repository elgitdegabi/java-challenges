package com.example.java.challenge.matrix;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Given a square matrix, calculate the absolute difference between the sums of its diagonals. .
 * For example:
 * Case 1: 1, 2, 3; 4, 5, 6; 9, 8, 9 -> 15 - 17 = 2
 * Case 2: 11, 2, 4; 4, 5, 6; 10, 8, -12-> 4 - 19 = 15
 */
@Slf4j
public class SquareMatrixDifferenceChallenge {

    /**
     * Calculate absolute difference between matrix's diagonal from given matrix
     *
     * @param arr {@link List<List<Integer>>}
     * @return absolute difference between matrix's diagonals
     */
    public static int calculate(final List<List<Integer>> arr) {
        /** TODO: classic java implementation
         int leftToRightSum = 0, rightToLefSum = 0;

         for(int i = 0; i < arr.size(); i ++) {
         for (int j = 0; j < arr.get(i).size(); j++) {
         leftToRightSum += (i == j? arr.get(i).get(j): 0);
         rightToLefSum += (arr.size() - 1 - i == j? arr.get(i).get(j): 0);
         }
         } **/

        AtomicInteger leftToRightSum = new AtomicInteger(0), rightToLefSum = new AtomicInteger(0);

        IntStream.range(0, arr.size()).forEach(i -> {
            IntStream.range(0, arr.get(i).size()).forEach(j -> {
                leftToRightSum.addAndGet(i == j ? arr.get(i).get(j) : 0);
                rightToLefSum.addAndGet(arr.size() - 1 - i == j ? arr.get(i).get(j) : 0);
            });
        });

        return Math.abs(leftToRightSum.intValue() - rightToLefSum.intValue());
    }
}
