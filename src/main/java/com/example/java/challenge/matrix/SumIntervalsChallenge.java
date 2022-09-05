package com.example.java.challenge.matrix;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Write a function called sumIntervals that accepts an array of intervals, and returns the sum of all the interval lengths.
 * Overlapping intervals should only be counted once.
 * Intervals are represented by a pair of integers in the form of an array.
 * The first value of the interval will always be less than the second value.
 * Interval example: [1, 5] is an interval from 1 to 5. The length of this interval is 4.
 * IMPORTANT:
 * - Your algorithm should be able to handle large intervals. All tested intervals are subsets of the range [-1000000000, 1000000000].
 * For example:
 * Case 1: {1, 4}, {1, 3}} -> 1 to 4 -> 4 - 1 = 3
 */
@Slf4j
public class SumIntervalsChallenge {

    /**
     * Sums given interval of integers
     *
     * @param intervals int[][] interval of integer numbers
     * @return int sum or 0
     */
    public static int sumIntervals(final int[][] intervals) {
        if (intervalsAreValid(intervals)) {
            Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));

            LinkedList<int[]> intervalList = new LinkedList<>();
            intervalList.add(intervals[0]);

            for (int i = 0; i < intervals.length; i++) {
                int lastFloor = intervalList.getLast()[0];
                int lastCeil = intervalList.getLast()[1];
                int currentFloor = intervals[i][0];
                int currentCeil = intervals[i][1];

                if (currentFloor <= lastCeil) {
                    intervalList.getLast()[1] = Math.max(currentCeil, lastCeil);
                } else {
                    intervalList.add(intervals[i]);
                }
            }

            return intervalList.stream().mapToInt(value -> value[1] - value[0]).sum();
        }

        return 0;
    }

    /**
     * Verifies if given intervals are well formed
     *
     * @param intervals int[][] interval of integer numbers
     * @return boolean value if all intervals are well formed
     */
    private static boolean intervalsAreValid(final int[][] intervals) {
        if (intervals == null || intervals.length < 1) {
            return false;
        }

        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i].length != 2) {
                return false;
            }
            for (int j = 0; j < 2; j++) {
                if (intervals[i][0] >= intervals[i][1]) {
                    return false;
                }
            }
        }

        return true;
    }
}
