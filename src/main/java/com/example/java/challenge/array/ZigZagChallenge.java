package com.example.java.challenge.array;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

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
            Arrays.sort(a);
            int mid = n /2;
            int temp = a[mid];
            a[mid] = a[n - 1];
            a[n - 1] = temp;

            int st = mid + 1;
            int ed = n - 1 - 1;
            while(st <= ed){
                temp = a[st];
                a[st] = a[ed];
                a[ed] = temp;
                st = st + 1;
                ed = ed - 1;
            }
            for(int i = 0; i < n; i++){
                if(i > 0) System.out.print(" ");
                System.out.print(a[i]);
            }
            System.out.println();
        }
    }
}
