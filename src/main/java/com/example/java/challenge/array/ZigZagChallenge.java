package com.example.java.challenge.array;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Given an array of n distinct integers, transform the array into a zig-zag sequence by permuting the array elements.
 * A sequence will be called a zig-zag sequence if the first k elements in the sequence are in increasing order and
 * the last k elements are in decreasing order, where k = (n + 1)/2.
 * You need to find the lexicographically smallest zig-zag sequence of the given array.
 * For example:
 * Case 1: 1, 4, 5, 3, 2 ->  1, 2, 5, 4, 3
 * Case 2: 1, 2, 3, 4, 5, 6, 7 ->  1, 2, 3, 7, 6, 5, 4
 */
@Slf4j
public class ZigZagChallenge {
    public static void main(String[] args) {
        log.info("findZigZagSequence start");
        ZigZagSequence.findZigZagSequence(new int[]{1, 4, 5, 3, 2}, 5);
        ZigZagSequence.findZigZagSequence(new int[]{1, 2, 3, 4, 5, 6, 7}, 7);
        log.info("findZigZagSequence end");
    }

    /**
     * ZigZagSequence class
     */
    private static class ZigZagSequence {

        /**
         * Finds zig-zag array from given array
         * @param a int array
         * @param n array size
         * @return zig-zag array
         */
        public static void findZigZagSequence(final int [] a, final int n){
            int middle = n /2; // switch between middle and last position
            Arrays.sort(a);

            IntStream.range(0, a.length)
                    .map(i -> i < middle? a[i]: a[a.length - 1 - (i - middle)])
                    .forEach(value -> System.out.print(value + " "));
            System.out.println();
        }
    }
}
