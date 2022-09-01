package com.example.java.challenge.array;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Your goal in this kata is to implement a difference function, which subtracts one list from another and returns the result.
 * It should remove all values from list a, which are present in list b keeping their order.
 * For example:
 * Case 1: [1, 2], [1] -> [2]
 */
@Slf4j
public class ArrayDiffChallenge {

    /**
     * Calculates diff between given arrays (a minus b)
     *
     * @param a int[]
     * @param b int[]
     * @return diff between given arrays (a minus b)
     */
    public static int[] arrayDiff(final int[] a, final int[] b) {
        if (!(a != null && b != null)) {
            return new int[0];
        }

        List<Integer> aList = Arrays.stream(a).mapToObj(Integer::valueOf).collect(Collectors.toList());
        List<Integer> bList = Arrays.stream(b).mapToObj(Integer::valueOf).collect(Collectors.toList());

        return aList.stream().filter(val -> !bList.contains(val)).mapToInt(Integer::intValue).toArray();
    }
}
