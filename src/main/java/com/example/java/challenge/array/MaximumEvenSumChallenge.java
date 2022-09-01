package com.example.java.challenge.array;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * One of the shops is offering discount coupons based on a puzzling problem.
 * There are n tags where each tag has a value denoted by val[i]. A customer needs to choose the tags in such a way that the sym of values is even.
 * The goal is to find the maximum possible even sum of values of tags that can be chosen:
 * - there is at least one tag with an even value
 * - tags can be positive or negative values
 * - it can be possible to choose no tags at all
 * For example:
 * Case 1: 6, 3, 4, -1, 9, 17 -> 38
 * Case 2: -1, -2, -3, 8, 7 -> 14
 * Case 3: 2, 3, 6, -5, 10, 1,1 -> 22
 * Case 4: 2, 3, 6, 4, 10, 1, 1 -> 26
 * Case 5: -2, -3, -6, -4, -10, -1, -1 -> -2
 */
@Slf4j
public class MaximumEvenSumChallenge {
    public static void main(String[] args) {
        log.info("maximum even sum start");
        log.info("maximum even sum result: {}", MaximumEvenSum.getMaximumEvenSum(List.of(6, 3, 4, -1, 9, 17)));
        log.info("maximum even sum result: {}", MaximumEvenSum.getMaximumEvenSum(List.of(-1, -2, -3, 8, 7)));
        log.info("maximum even sum result: {}", MaximumEvenSum.getMaximumEvenSum(List.of(2, 3, 6, -5, 10, 1,1)));
        log.info("maximum even sum result: {}", MaximumEvenSum.getMaximumEvenSum(List.of(2, 3, 6, 4, 10, 1, 1)));
        log.info("maximum even sum result: {}", MaximumEvenSum.getMaximumEvenSum(List.of(-2, -3, -6, -4, -10, -1, -1)));
        log.info("maximum even sum result: {}", MaximumEvenSum.getMaximumEvenSum(List.of(-1, -1, -1, -2)));
        log.info("maximum even sum result: {}", MaximumEvenSum.getMaximumEvenSum(List.of(1, 2)));
        log.info("maximum even sum end");
    }

    /**
     * MaximumEvenSum class
     */
    private static class MaximumEvenSum {

        /**
         * Calculates maximum even sum from given {@link List<Integer>}
         * @param val {@link List<Integer>}
         * @return maximum even sum
         */
        public static long getMaximumEvenSum(List<Integer> val) {
            int[] array = IntStream.range(0, val.size()).map(i -> val.get(i)).sorted().toArray();
            long sum = Arrays.stream(array).filter(value -> value > 0).sum();

            if (sum == 0) { // no positive value so take max even negative value
                sum = Arrays.stream(array).filter(value -> value % 2 == 0).max().orElse(0);
            }

            if (sum % 2 != 0) {
                int positiveMinValue = Arrays.stream(array)
                        .filter(value -> value > 0) // only positive values
                        .filter(value -> value % 2 != 0) // only positive not even values
                        .min().orElse(Integer.MAX_VALUE);
                int negativeMaxValue = Arrays.stream(array)
                        .filter(value -> value < 0) // only negative values
                        .filter(value -> value % 2 != 0) // only negative not even values
                        .max().orElse(Integer.MAX_VALUE);

                if (Math.abs(positiveMinValue) < Math.abs(negativeMaxValue)) {
                    sum -= positiveMinValue;
                } else {
                    sum += negativeMaxValue;
                }
            }

            return sum;
        }
    }
}
