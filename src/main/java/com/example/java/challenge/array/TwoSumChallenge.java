package com.example.java.challenge.array;

import lombok.extern.slf4j.Slf4j;

/**
 * Write a function that takes an array of integers and a target number.
 * It should find two different items in the array that, when added together, give the target value.
 * The indices of these items should then be returned in a tuple / list (depending on your language) like so: (index1, index2).
 * For example:
 * Case 1: {1234, 5678, 9012} & 14690 -> {1, 2}
 */
@Slf4j
public class TwoSumChallenge {
    /**
     * Calculates indexes of array sum values equals to given target
     *
     * @param numbers int[]
     * @param target  int
     * @return int[] indexes
     */
    public static int[] twoSum(final int[] numbers, final int target) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (target == numbers[i] + numbers[j]) {
                    return new int[]{i, j};
                }
            }
        }

        return null;
    }
}
