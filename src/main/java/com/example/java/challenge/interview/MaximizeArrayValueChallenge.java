package com.example.java.challenge.interview;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Given an array of n positive integers, the following operation can be performed any number of times (use a 1-based index for the array):
 * • Choose any / such that 2 <= i <= n.
 * • Choose any x such that 1 <= x <= arr[i]
 * • Set arr[i-1] to arr[i-1] + x
 * • Set arr[i] to arr[i] - x
 *
 * Minimize the maximum value of arr using the operation and return the value
 */
@Slf4j
public class MaximizeArrayValueChallenge {
    public static void main(String[] args) {
        log.info("maximize array value start");

        //List<Integer> arr = List.of(Integer.valueOf(3), Integer.valueOf(5), Integer.valueOf(15), Integer.valueOf(19));
        List<Integer> arr = List.of(Integer.valueOf(1), Integer.valueOf(5), Integer.valueOf(7), Integer.valueOf(6));

        MaximizeArrayValue.minimizeMaximumValue(arr);

        log.info("maximize array value end");
    }

    /**
     * Maximize array value class
     */
    private static class MaximizeArrayValue {

        /**
         * Minimize Maximum Value from given array arr
         * @param arr {@link List<Integer>}
         */
        public static void minimizeMaximumValue(final List<Integer> arr) {
            int[] array = arr.stream().mapToInt(Integer::intValue).toArray();

            int max = Arrays.stream(array).max().getAsInt();
            int operation = 1;

            while (getMaxIndex(array, max) > 0) {
                log.info("-------------------------------------------------------------------------------------------");
                log.info("operation #:{}, array before: {}", operation, array);

                int i = getMaxIndex(array, max);
                int x = getMaxIndex(array, Arrays.stream(array).filter(a -> a != Arrays.stream(array).max().getAsInt()).max().getAsInt());

                array[i - 1] = array[i - 1] + (x + 1);
                array[i] = array[i] - (x + 1);

                log.info("operation #:{}, array after : {}", operation, array);

                max = Arrays.stream(array).max().getAsInt();
                operation++;
            }

            int minimizedMaximumValue = Arrays.stream(array).max().getAsInt();
            log.info("-----------------------------------------------------------------------------------------------");
            log.info("minimized maximum value: {}", minimizedMaximumValue);
            log.info("-----------------------------------------------------------------------------------------------");
        }

        /**
         * Gets index value from given max value
         * @param array array of int
         * @param maxValue int
         * @return index of max value
         */
        private static int getMaxIndex(final int[] array, int maxValue) {
            return IntStream.range(0, array.length).filter(index -> maxValue == array[index]).findFirst().getAsInt();
        }
    }
}
